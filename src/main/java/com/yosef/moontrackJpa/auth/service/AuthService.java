package com.yosef.moontrackJpa.auth.service;

import org.springframework.security.core.Authentication;

public interface AuthService {
  String generateToken(Authentication authentication);
}
