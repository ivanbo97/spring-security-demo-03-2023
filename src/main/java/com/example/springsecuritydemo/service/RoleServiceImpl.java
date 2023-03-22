package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.model.Role;
import com.example.springsecuritydemo.model.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;

  @Override
  public Role findByName(String roleName) {
    log.info("Fetching role with name [{}]", roleName);
    return roleRepository
        .findByName(roleName)
        .orElseThrow(
            () ->
                new EntityNotFoundException(
                    "Role with name [%s] doesn't exist!".formatted(roleName)));
  }

  @Override
  public Role save(Role newRole) {
    String roleName = newRole.getName();
    roleRepository
        .findByName(roleName)
        .ifPresent(
            role -> {
              throw new EntityExistsException(
                  "Role with name [%s] already exists".formatted(roleName));
            });
    log.info("Saving role with name [{}]", newRole.getName());
    return roleRepository.save(newRole);
  }
}
