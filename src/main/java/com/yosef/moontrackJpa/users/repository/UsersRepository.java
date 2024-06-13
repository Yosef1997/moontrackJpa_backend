package com.yosef.moontrackJpa.users.repository;

import com.yosef.moontrackJpa.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {
  Optional<Users> findByEmail(String email);
}
