package com.yosef.moontrackJpa.auth.service;

import com.yosef.moontrackJpa.auth.dto.LoginRequestDto;
import com.yosef.moontrackJpa.auth.dto.SetupPinRequestDto;
import org.springframework.security.core.Authentication;

public interface AuthService {
  String generateToken(Authentication authentication);

  String resetPassword(LoginRequestDto requestDto);

  String setupPin(SetupPinRequestDto requestDto);
}
