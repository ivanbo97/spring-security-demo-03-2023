package com.example.springsecuritydemo.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findById(Long aLong);

  Optional<User> findByUsername(String username);
}
