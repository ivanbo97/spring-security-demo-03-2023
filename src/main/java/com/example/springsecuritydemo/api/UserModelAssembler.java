package com.example.springsecuritydemo.api;

import com.example.springsecuritydemo.model.Role;
import com.example.springsecuritydemo.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserModelAssembler {

  public static UserModel toModel(User user) {
    return UserModel.builder()
        .name(user.getName())
        .username(user.getUsername())
        .email(user.getEmail())
        .roles(user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList()))
        .build();
  }

  public User toEntity(UserModel userModel) {
    List<Role> userRoles =
        userModel.getRoles().stream()
            .map(role -> new Role(null, role))
            .collect(Collectors.toList());
    return User.builder()
        .name(userModel.getName())
        .username(userModel.getUsername())
        .email(userModel.getEmail())
        .roles(userRoles)
        .build();
  }
}
