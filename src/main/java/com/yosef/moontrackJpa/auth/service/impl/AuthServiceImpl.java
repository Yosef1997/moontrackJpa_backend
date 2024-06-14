package com.yosef.moontrackJpa.auth.service.impl;

import com.yosef.moontrackJpa.auth.dto.LoginRequestDto;
import com.yosef.moontrackJpa.auth.dto.SetupPinRequestDto;
import com.yosef.moontrackJpa.auth.service.AuthService;
import com.yosef.moontrackJpa.exceptions.applicationException.ApplicationException;
import com.yosef.moontrackJpa.users.entity.Users;
import com.yosef.moontrackJpa.users.repository.UsersRepository;
import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log
public class AuthServiceImpl implements AuthService {

  private final JwtEncoder jwtEncoder;

  private final PasswordEncoder passwordEncoder;

  private final UsersRepository usersRepository;

  public AuthServiceImpl(JwtEncoder jwtEncoder, PasswordEncoder passwordEncoder, UsersRepository usersRepository) {
    this.jwtEncoder = jwtEncoder;
    this.passwordEncoder = passwordEncoder;
    this.usersRepository = usersRepository;
  }

  @Override
  public String generateToken(Authentication authentication) {
    Instant now = Instant.now();

    String scope = authentication.getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(" "));

    JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(now)
            .expiresAt(now.plus(10, ChronoUnit.HOURS))
            .subject(authentication.getName())
            .claim("scope", scope)
            .build();

    return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
  }

  @Override
  public String resetPassword(LoginRequestDto requestDto) {
    Optional<Users> usersOptional = usersRepository.findByEmail(requestDto.getEmail());
    if (usersOptional.isPresent()) {
      Users user = usersOptional.get();
      String encodedNewPassword = passwordEncoder.encode(requestDto.getPassword());
      user.setPassword(encodedNewPassword);
      usersRepository.save(user);
      return "Reset Password success";
    }
    throw new ApplicationException("User not found");
  }

  @Override
  public String setupPin(SetupPinRequestDto requestDto) {
    Optional<Users> usersOptional = usersRepository.findByEmail(requestDto.getEmail());
    if (usersOptional.isPresent()) {
      Users user = usersOptional.get();
      String encodedPin = passwordEncoder.encode(requestDto.getPin());
      user.setPin(encodedPin);
      usersRepository.save(user);
      return "Setup pin success";
    }
    throw new ApplicationException("Setup pin failed");
  }


}
