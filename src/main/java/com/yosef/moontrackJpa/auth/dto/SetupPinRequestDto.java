package com.yosef.moontrackJpa.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SetupPinRequestDto {
  @NotBlank(message = "Email is required")
  private String email;

  @NotBlank(message = "Pin is required")
  private String pin;
}
