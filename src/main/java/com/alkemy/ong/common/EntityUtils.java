package com.alkemy.ong.common;

import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.response.OrganizationResponse;
import com.alkemy.ong.model.response.UserResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;

public class EntityUtils {

  private EntityUtils() {
  }

  public static OrganizationResponse convertTo(Organization organization) {
    OrganizationResponse organizationResponse = new OrganizationResponse();
    BeanUtils.copyProperties(organization, organizationResponse);
    return organizationResponse;
  }
  public static List<UserResponse> convertTo(List<User> users) {
    List<UserResponse> userResponseList = new ArrayList<>();
    for (User user:users) {
      userResponseList.add(convertTo(user));
    }
    return userResponseList;
  }

  public static UserResponse convertTo(User user){
    UserResponse userResponse = new UserResponse();
    userResponse.setId(user.getId());
    userResponse.setFirstName(user.getFirstName());
    userResponse.setLastName(user.getLastName());
    userResponse.setEmail(user.getEmail());
    userResponse.setPhoto(user.getPhoto());
    userResponse.setRoles(user.getRoles());
    return userResponse;
  }
}