package br.com.fiap.sphere.post;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.sphere.post.dto.PostRequest;
import br.com.fiap.sphere.user.User;
import br.com.fiap.sphere.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/posts")
public class PostController {

  @Autowired
  PostService service;

  @Autowired
  UserRepository userRepository;

  @GetMapping()
  public Page<Post> findAll(@PageableDefault(size = 3) Pageable pageable) {
    return service.findAll(pageable);
  }

  @PostMapping
  public Post create(@RequestBody PostRequest postRequest) {
    var email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    var user = userRepository.findByEmail(email).orElseThrow(
      () -> new UsernameNotFoundException("User not found")
    )

    Post post = postRequest.toModel(user);
    return service.create(post);
  }

}
