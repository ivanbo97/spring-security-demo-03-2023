package com.example.springsecuritydemo;

import com.example.springsecuritydemo.api.UserModel;
import com.example.springsecuritydemo.model.Role;
import com.example.springsecuritydemo.service.RoleService;
import com.example.springsecuritydemo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

@SpringBootApplication
public class SpringSecurityDemoApplication {

  private final List<Role> INITIAL_ROLES =
      List.of(
          new Role(null, "ROLE_USER"),
          new Role(null, "ROLE_MANAGER"),
          new Role(null, "ROLE_ADMIN"),
          new Role(null, "ROLE_SUPER_ADMIN"));

  private final List<UserModel> INITIAL_USERS =
      List.of(
          new UserModel("Ivan Ivanov", "ivanov", "i.ivanov@gmail.com", "1234", new ArrayList<>()),
          new UserModel("Petar Ivanov", "pivanov", "p.ivanov@gmail.com", "1234", new ArrayList<>()),
          new UserModel(
              "Dimitar Ivanov", "divanov", "i.ivanov@gmail.com", "1234", new ArrayList<>()),
          new UserModel(
              "Georgi Ivanov", "givanov", "i.ivanov@gmail.com", "1234", new ArrayList<>()));

  private final Map<String, List<String>> INITIAL_USER_ROLES =
      Map.ofEntries(
          entry("ivanov", List.of("ROLE_USER", "ROLE_MANAGER")),
          entry("pivanov", List.of("ROLE_MANAGER")),
          entry("divanov", List.of("ROLE_ADMIN")),
          entry("givanov", List.of("ROLE_SUPER_ADMIN", "ROLE_ADMIN", "ROLE_USER")));

  public static void main(String[] args) {
    SpringApplication.run(SpringSecurityDemoApplication.class, args);
  }

  @Bean
  CommandLineRunner run(UserService userService, RoleService roleService) {
    return args -> {
      INITIAL_ROLES.forEach(role -> roleService.save(role));
      INITIAL_USERS.forEach(user -> userService.saveUser(user));
      INITIAL_USER_ROLES.forEach(
          (username, roles) -> userService.attachRolesToUser(username, roles));
    };
  }
}
