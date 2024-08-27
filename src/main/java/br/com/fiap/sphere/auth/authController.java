package br.com.fiap.sphere.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class authController {

  @Autowired
  AuthService authService;

  @PostMapping("/login")
  public Token login(@RequestBody Credentials credentials) {
    return authService.login(credentials);
  }

}
