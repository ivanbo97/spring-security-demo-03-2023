package com.example.springsecuritydemo.api;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRoleRequest {
  String username;
  String roleName;
}
