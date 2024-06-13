package com.yosef.moontrackJpa.users.service;

import com.yosef.moontrackJpa.users.dto.RegisterDTO;
import com.yosef.moontrackJpa.users.entity.Users;

import java.util.List;

public interface UsersService {
  Users register(RegisterDTO request);

  Users findByEmail(String email);

  Users findById(Long id);

  List<Users> findAll();
}
