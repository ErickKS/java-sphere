package br.com.fiap.sphere.user.dto;

import java.time.LocalDateTime;

import br.com.fiap.sphere.user.User;

public record UserResponse(
    Long id,
    String name,
    String bio,
    String email,
    String password,
    LocalDateTime createdAt) {
  public static UserResponse fromModel(User user) {
    return new UserResponse(
        user.getId(),
        user.getName(),
        user.getBio(),
        user.getEmail(),
        user.getPassword(),
        user.getCreatedAt());
  }
}
