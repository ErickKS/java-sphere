package br.com.fiap.sphere.auth;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.fiap.sphere.user.User;
import br.com.fiap.sphere.user.UserRepository;

@Service
public class TokenService {

  @Autowired
  UserRepository userRepository;

  private Algorithm algorithm;

  public TokenService(UserRepository userRepository, @Value("${jwt.secret}") String secret) {
    this.userRepository = userRepository;
    this.algorithm = Algorithm.HMAC256("assinatura");
  }

  public Token create(Credentials credentials) {
    var expiresAt = LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.ofHours(-3));

    var token = JWT.create()
        .withSubject(credentials.email())
        .withClaim("role", "ADMIN")
        .withExpiresAt(expiresAt)
        .sign(algorithm);

    return new Token(token, credentials.email());
  }

  public User getUserFromToken(String token) {
    var email = JWT.require(algorithm)
        .build()
        .verify(token)
        .getSubject();

    return userRepository.findByEmail(email).orElseThrow(
        () -> new UsernameNotFoundException("User not found"));
  }

}
