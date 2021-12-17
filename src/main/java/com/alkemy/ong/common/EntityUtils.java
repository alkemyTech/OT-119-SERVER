package com.alkemy.ong.common;

import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.response.UserDetailsResponse;
import org.springframework.stereotype.Service;

@Service
public class EntityUtils {


  public UserDetailsResponse userEntity2DTO(User userEntity) {
    UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
    userDetailsResponse.setFirstName(userEntity.getFirstName());
    userDetailsResponse.setLastName(userEntity.getLastName());
    userDetailsResponse.setEmail(userEntity.getEmail());
    return userDetailsResponse;
  }


}
