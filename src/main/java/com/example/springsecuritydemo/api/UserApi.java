package com.example.springsecuritydemo.api;

import com.example.springsecuritydemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserApi {

  private final UserService userService;

  @GetMapping
  public ResponseEntity<List<UserModel>> getUsers() {
    return ResponseEntity.ok(
        userService.getAllUsers().stream()
            .map(UserModelAssembler::toModel)
            .collect(Collectors.toList()));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<UserModel> createUser(@RequestBody UserModel newUser) {
    userService.saveUser(newUser);
    return new ResponseEntity<>(newUser, HttpStatus.CREATED);
  }

  @PostMapping("/roles")
  public ResponseEntity addRoleToUser(@RequestBody UserRoleRequest userRoleRequest) {
    userService.attachRoleToUser(userRoleRequest.getUsername(), userRoleRequest.getRoleName());
    return ResponseEntity.ok().build();
  }
}
