package br.com.fiap.sphere.user;

import java.util.List;

import br.com.fiap.sphere.user.dto.UserProfileResponse;
import br.com.fiap.sphere.user.dto.UserRequest;
import br.com.fiap.sphere.user.dto.UserResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  UserService service;

  @GetMapping
  public List<User> findAll() {
    return service.findAll();
  }

  @PostMapping
  public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest, UriComponentsBuilder uriBuilder) {
    var user = service.create(userRequest.toModel());

    var uri = uriBuilder
        .path("users/{id}")
        .buildAndExpand(user.getId())
        .toUri();

    return ResponseEntity
        .created(uri)
        .body(UserResponse.fromModel(user));
  }

  @GetMapping("profile")
  public UserProfileResponse getUserProfile() {
    var email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    return service.getUserProfile(email);
  }

  @PostMapping("avatar")
  public void uploadAvatar(@RequestBody MultipartFile file) {
    var email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    service.uploadUserAvatar(email, file);
  }

}
