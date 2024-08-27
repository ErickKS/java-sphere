package br.com.fiap.sphere.auth;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class TokenService {

  public Token create(Credentials credentials) {
    Algorithm algorithm = Algorithm.HMAC256("assinatura");

    var expiresAt = LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.ofHours(-3));

    var token = JWT.create()
        .withSubject(credentials.email())
        .withClaim("role", "ADMIN")
        .withExpiresAt(expiresAt)
        .sign(algorithm);

    return new Token(token, credentials.email());
  }

}
