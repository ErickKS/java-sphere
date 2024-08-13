package br.com.fiap.sphere.user;

import java.util.List;

import br.com.fiap.sphere.user.dto.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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
  public ResponseEntity<User> create(@RequestBody UserRequest userRequest, UriComponentsBuilder uriBuilder) {
    var user = service.create(userRequest.toModel());

    var uri = uriBuilder
        .path("users/{id}")
        .buildAndExpand(user.getId())
        .toUri();

    return ResponseEntity
        .created(uri)
        .body(user);
  }

}
