package br.com.fiap.sphere.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.fiap.sphere.user.UserRepository;

@Service
public class AuthService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  TokenService tokenService;

  public Token login(Credentials credentials) {
    var user = userRepository.findByEmail(credentials.email()).orElseThrow(() -> new RuntimeException("Access Denied"));

    if (!passwordEncoder.matches(credentials.password(), user.getPassword())) {
      throw new RuntimeException("Access Denied");
    }

    return tokenService.create(credentials);
  }

}
