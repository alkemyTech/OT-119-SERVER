package com.alkemy.ong.config.seeder;

import com.alkemy.ong.config.security.ApplicationRole;
import com.alkemy.ong.model.entity.Role;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.repository.IRoleRepository;
import com.alkemy.ong.repository.IUserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersDatabaseSeeder {

  private static final String PASSWORD = "test1234";
  private static final String HOST_EMAIL = "@test.com";
  private static final String DEFAULT_FIRST_NAME = "Test";

  @Autowired
  private IUserRepository userRepository;
  @Autowired
  private IRoleRepository roleRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @EventListener
  public void seed(ContextRefreshedEvent event) {
    List<Role> roles = roleRepository.findAll();
    if (roles.isEmpty()) {
      createRoles();
    }

    List<User> users = userRepository.findAll();
    if (users.isEmpty()) {
      createUsers();
    }
  }

  private void createUsers(ApplicationRole applicationRole) {
    for (int index = 0; index < 10; index++) {
      User user = new User();
      user.setFirstName(DEFAULT_FIRST_NAME);
      user.setLastName(applicationRole.getName() + index);
      user.setEmail(applicationRole.getName() + index + HOST_EMAIL);
      user.setPassword(passwordEncoder.encode(PASSWORD));
      List<Role> roles = new ArrayList<>();
      roles.add(roleRepository.findByName(applicationRole.getFullRoleName()));
      user.setRoles(roles);
      userRepository.save(user);
    }
  }

  private void createRole(Long id, ApplicationRole applicationRole) {
    Role role = new Role();
    role.setId(id);
    role.setName(applicationRole.getFullRoleName());
    role.setDescription(applicationRole.getName());
    roleRepository.save(role);
  }

  private void createRoles() {
    createRole(1L, ApplicationRole.ADMIN);
    createRole(2L, ApplicationRole.USER);
  }

  private void createUsers() {
    createUsers(ApplicationRole.ADMIN);
    createUsers(ApplicationRole.USER);
  }
}


