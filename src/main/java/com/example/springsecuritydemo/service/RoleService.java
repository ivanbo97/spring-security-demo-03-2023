package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.model.Role;

public interface RoleService {

  Role findByName(String roleName);

  Role save(Role newRole);
}
