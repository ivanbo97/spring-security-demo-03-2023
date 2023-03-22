package com.example.springsecuritydemo.api;

import com.example.springsecuritydemo.model.Role;
import com.example.springsecuritydemo.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/roles")
@RequiredArgsConstructor
public class RoleApi {

  private final RoleService roleService;

  @PostMapping
  public ResponseEntity<Role> createRole(Role newRole) {
    return new ResponseEntity<>(roleService.save(newRole), HttpStatus.CREATED);
  }
}
