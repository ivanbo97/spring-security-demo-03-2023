package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.api.UserModel;
import com.example.springsecuritydemo.model.User;

import java.util.List;

public interface UserService {

  User saveUser(UserModel newUser);

  void attachRoleToUser(String username, String roleName);

  void attachRolesToUser(String username,List<String> roles);

  User getByUsername(String username);

  List<User> getAllUsers();
}
