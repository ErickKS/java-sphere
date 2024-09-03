package br.com.fiap.sphere.post;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/posts")
public class PostController {

  @Autowired
  PostService service;

  @GetMapping()
  public Page<Post> findAll(@PageableDefault(size = 3) Pageable pageable) {
    return service.findAll(pageable);
  }

}
