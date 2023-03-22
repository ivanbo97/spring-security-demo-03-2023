package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.api.UserModel;
import com.example.springsecuritydemo.api.UserModelAssembler;
import com.example.springsecuritydemo.model.Role;
import com.example.springsecuritydemo.model.User;
import com.example.springsecuritydemo.model.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

  private final RoleService roleService;
  private final UserRepository userRepository;

  private final UserModelAssembler userModelAssembler;

  @Override
  public User saveUser(UserModel newUser) {
    String username = newUser.getUsername();
    userRepository
        .findByUsername(username)
        .ifPresent(
            user -> {
              throw new EntityExistsException("Username [%s] already exists.".formatted(username));
            });
    log.info("Saving new user with username [{}]", newUser.getUsername());

    return userRepository.save(userModelAssembler.toEntity(newUser));
  }

  @Override
  public void attachRoleToUser(String username, String roleName) {
    User user = getByUsername(username);
    boolean isRoleAlreadyAttached =
        user.getRoles().stream().filter(role -> role.getName().equals(roleName)).count() > 0;
    if (isRoleAlreadyAttached) {
      log.error("Username [{}] already has role [{}].", username, roleName);
      return;
    }
    Role role = roleService.findByName(roleName);
    user.getRoles().add(role);
    log.info("Role [{}] was added to user [{}]", roleName, username);
  }

  @Override
  public void attachRolesToUser(String username, List<String> roles) {
    roles.forEach(role -> attachRoleToUser(username, role));
  }

  @Override
  public User getByUsername(String username) {
    log.info("Fetching user with username [{}]", username);
    return userRepository
        .findByUsername(username)
        .orElseThrow(
            () ->
                new EntityNotFoundException(
                    "User with username [%s] doesn't exist!".formatted(username)));
  }

  @Override
  public List<User> getAllUsers() {
    log.info("Fetching all users...");
    return userRepository.findAll();
  }
}
