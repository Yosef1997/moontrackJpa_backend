package com.yosef.moontrackJpa.users.service;

import com.yosef.moontrackJpa.exceptions.applicationException.ApplicationException;
import com.yosef.moontrackJpa.users.dto.RegisterDTO;
import com.yosef.moontrackJpa.users.entity.Users;
import com.yosef.moontrackJpa.users.repository.UsersRepository;
import lombok.extern.java.Log;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log
public class UsersServiceImpl implements UsersService{

  private final UsersRepository usersRepository;
  private final PasswordEncoder passwordEncoder;

  public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
    this.usersRepository = usersRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public Users register(RegisterDTO request) {
    Users newUser = request.toEntity();
    var password = passwordEncoder.encode(newUser.getPassword());
    newUser.setPassword(password);
    return usersRepository.save(newUser);
  }

  @Override
  public Users findByEmail(String email) {
    return usersRepository.findByEmail(email).orElseThrow(() -> new ApplicationException("User not found"));
  }

  @Override
  public Users findById(Long id) {
    return usersRepository.findById(id).orElseThrow(() -> new ApplicationException("User not found"));
  }

  @Override
  public List<Users> findAll() {
    return usersRepository.findAll();
  }
}
