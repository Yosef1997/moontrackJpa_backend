package com.yosef.moontrackJpa.auth;

import com.yosef.moontrackJpa.auth.dto.LoginRequestDto;
import com.yosef.moontrackJpa.auth.dto.LoginResponseDto;
import com.yosef.moontrackJpa.auth.dto.SetupPinRequestDto;
import com.yosef.moontrackJpa.auth.entity.UserAuth;
import com.yosef.moontrackJpa.auth.service.AuthService;
import com.yosef.moontrackJpa.response.Response;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@Log
public class AuthController {
  private final AuthService authService;

  private final AuthenticationManager authenticationManager;

  public AuthController(AuthService authService, AuthenticationManager authenticationManager) {
    this.authService = authService;
    this.authenticationManager = authenticationManager;
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequestDto login) throws IllegalAccessException {
    log.info("User login request received for user: " + login.getEmail());
    Authentication authentication =
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            login.getEmail(),
                            login.getPassword()
                    ));
    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserAuth userDetails = (UserAuth) authentication.getPrincipal();
    //    Get logged-in user's role
    //    userDetails.getAuthorities().forEach(authority -> {
    //      log.info(authority.getAuthority());
    //    });
    log.info("Token requested for user :" + userDetails.getUsername() + " with roles: " + userDetails.getAuthorities().toArray()[0]);
    String token = authService.generateToken(authentication);

    LoginResponseDto responseDto = new LoginResponseDto();
    responseDto.setMessage("User logged in successfully");
    responseDto.setToken(token);
    return Response.successResponse(responseDto.getMessage(), responseDto);
  }

  @PostMapping("/forgot-password")
  public ResponseEntity<Response<Object>> forgotPassword (@Validated @RequestBody LoginRequestDto requestDto) {
    return Response.successResponse(authService.resetPassword(requestDto));
  }

  @PostMapping("/setup-pin")
  public ResponseEntity<Response<Object>> setupPin (@Validated @RequestBody SetupPinRequestDto requestDto) {
    return Response.successResponse(authService.setupPin(requestDto));
  }
}
