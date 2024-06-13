package com.yosef.moontrackJpa.users.dto;

import com.yosef.moontrackJpa.users.entity.Users;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterDTO {
  @NotBlank(message = "Name is required")
  private String name;

  @NotBlank(message = "Email is required")
  private String email;

  @NotBlank(message = "Password is required")
  private String password;

  @NotNull
  @Min(1)
  private int activeCurrency;

  @NotNull
  @Min(1)
  private int activeLanguage;

  public Users toEntity() {
    Users user = new Users();
    user.setName(name);
    user.setEmail(email);
    user.setPassword(password);
    user.setActiveCurrency(activeCurrency);
    user.setActiveLanguage(activeLanguage);
    return user;
  }
}
