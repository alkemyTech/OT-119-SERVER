package com.alkemy.ong.common;

import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.UserRequest;
import com.alkemy.ong.model.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserModelUtils {

  @Autowired
  private PasswordEncoder passwordEncoder;

  public User userDTO2Entity(UserRequest userdto) {
    User user = new User();
    user.setFirstName(userdto.getFirstname());
    user.setLastName(userdto.getLastname());
    user.setEmail(userdto.getEmail());
    user.setPassword(passwordEncoder.encode(userdto.getPassword()));
    user.setPhoto(userdto.getPhoto());
    return user;
  }

  public UserResponse userEntity2DTO(User userentity) {
    UserResponse userResponse = new UserResponse();
    userResponse.setFirstname(userentity.getFirstName());
    userResponse.setLastname(userentity.getLastName());
    userResponse.setEmail(userentity.getEmail());
    userResponse.setPhoto(userentity.getPhoto());
    return userResponse;
  }


}
