package br.com.fiap.sphere.post;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/posts")
public class PostController {

  @Autowired
  PostService service;

  @GetMapping()
  public List<Post> findAll() {
    return service.findAll();
  }

}
