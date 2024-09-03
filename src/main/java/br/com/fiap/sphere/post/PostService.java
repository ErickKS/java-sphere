package br.com.fiap.sphere.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostService {

  @Autowired
  PostRepository repository;

  public Page<Post> findAll(Pageable pageable) {
    return repository.findAll(pageable);
  }

}
