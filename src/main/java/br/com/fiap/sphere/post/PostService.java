package br.com.fiap.sphere.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
  @Autowired
  PostRepository repository;

  public List<Post> findAll() {
    return repository.findAll();
  }

}
