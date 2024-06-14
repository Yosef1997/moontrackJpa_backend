package com.yosef.moontrackJpa.users;

import com.yosef.moontrackJpa.response.Response;
import com.yosef.moontrackJpa.users.dto.RegisterDTO;
import com.yosef.moontrackJpa.users.entity.Users;
import com.yosef.moontrackJpa.users.service.UsersService;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Log
public class UsersController {
  private final UsersService usersService;

  public UsersController(UsersService usersService, AuthenticationManager authenticationManager) {
    this.usersService = usersService;
  }

  @GetMapping("/")
  public ResponseEntity<Response<List<Users>>> getAllUsers() {
    return Response.successResponse("All registered users", usersService.findAll());
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@Validated @RequestBody RegisterDTO registerDTO) {
    return Response.successResponse("User register success", usersService.register(registerDTO));
  }

  @GetMapping("/profile/{id}")
  public ResponseEntity<Response<Users>> getProfile(@PathVariable Long id) {
    return Response.successResponse("Get profile success", usersService.findById(id));
  }

}
