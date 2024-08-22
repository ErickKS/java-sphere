package br.com.fiap.sphere.auth;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.fiap.sphere.user.UserRepository;

@RestController
public class authController {

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @PostMapping("/login")
  public Token login(@RequestBody Credentials credentials) {
    var user = userRepository.findByEmail(credentials.email()).orElseThrow(() -> new RuntimeException("Access Denied"));

    if (!passwordEncoder.matches(credentials.password(), user.getPassword())) {
      throw new RuntimeException("Access Denied");
    }
    ;

    Algorithm algorithm = Algorithm.HMAC256("assinatura");

    var expiresAt = LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.ofHours(-3));

    String token = JWT.create()
        .withSubject(credentials.email())
        .withClaim("role", "admin")
        .withExpiresAt(expiresAt)
        .sign(algorithm);

    return new Token(token, credentials.email());
  }

}
