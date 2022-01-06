package com.alkemy.ong.common.seeders;


import com.alkemy.ong.config.security.ApplicationRole;
import com.alkemy.ong.model.entity.Role;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.repository.IRoleRepository;
import com.alkemy.ong.repository.IUserRepository;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsersDatabaseSeeder {

  @Autowired
  private IUserRepository userRepository;
  @Autowired
  private IRoleRepository roleRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  protected final Log logger = LogFactory.getLog(getClass());

  public UsersDatabaseSeeder(
      IUserRepository userRepository,
      IRoleRepository roleRepository,
      PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @EventListener
  public void seed(ContextRefreshedEvent event) {
    createRoles();
    seedUsersTable();
  }


  private void createUsers(ApplicationRole applicationRole) {
    for (int i = 0; i < 10; i++) {
      List<Role> roles = new ArrayList<>();
      createRoles();
      User user = new User();
      user.setFirstName("Test");
      user.setLastName(applicationRole.getName() + i);
      user.setEmail(applicationRole.getName() + i + "@test.com");
      user.setPassword(passwordEncoder.encode("test1234"));
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

  private void seedUsersTable() {
    List<User> users = userRepository.findAll();
    if (users.isEmpty()) {
      createUsers(ApplicationRole.ADMIN);
      createUsers(ApplicationRole.USER);
      logger.info("Users Seeded");
    } else {
      logger.info("Users Seeding Not Required");
    }
  }


}


