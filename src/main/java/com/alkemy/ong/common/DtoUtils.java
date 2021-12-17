package com.alkemy.ong.common;

import com.alkemy.ong.config.security.ApplicationRole;
import com.alkemy.ong.model.entity.Role;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.UserDetailsRequest;
import com.alkemy.ong.service.abstraction.IRoleService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class DtoUtils {

  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private IRoleService roleService;

  public User userDTO2Entity(UserDetailsRequest userDetailsRequest) {
    User userEntity = new User();
    userEntity.setFirstName(userDetailsRequest.getFirstName());
    userEntity.setLastName(userDetailsRequest.getLastName());
    userEntity.setEmail(userDetailsRequest.getEmail());
    userEntity.setPassword(passwordEncoder.encode(userDetailsRequest.getPassword()));
    List<Role> roles = new ArrayList<>();
    roles.add(roleService.findBy(ApplicationRole.USER.getFullRoleName()));
    userEntity.setRoles(roles);
    return userEntity;
  }
}
