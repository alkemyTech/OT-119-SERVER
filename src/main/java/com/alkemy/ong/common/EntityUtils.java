package com.alkemy.ong.common;

import com.alkemy.ong.model.entity.Organization;
<<<<<<< Updated upstream
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.response.OrganizationResponse;
=======
import com.alkemy.ong.model.entity.Role;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.response.ListUserResponse;
import com.alkemy.ong.model.response.OrganizationResponse;
import com.alkemy.ong.model.response.RoleResponse;
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
  public static List<UserResponse> convertTo(List<User> users) {
    List<UserResponse> userResponseList = new ArrayList<>();
    for (User user:users) {
      userResponseList.add(convertTo(user));
    }
    return userResponseList;
  }

  public static UserResponse convertTo(User user){
=======

  public static ListUserResponse convertTo(List<User> users) {
    ListUserResponse userResponses = new ListUserResponse();
    for (User user : users) {
      userResponses.add(convertTo(user));
    }
    return userResponses;
  }

  public static RoleResponse convertToRole(Role role){
    RoleResponse roleResponse = new RoleResponse();
    roleResponse.setId(role.getId());
    roleResponse.setName(role.getName());
    roleResponse.setDescription(role.getDescription());
    return  roleResponse;
  }
  public static List<RoleResponse> convertToRoles(List<Role> roles){
    List<RoleResponse> roleResponses = new ArrayList<>();
    for (Role role: roles) {
      roleResponses.add(convertToRole(role));
    }
    return roleResponses;
  }

  public static UserResponse convertTo(User user) {
>>>>>>> Stashed changes
    UserResponse userResponse = new UserResponse();
    userResponse.setId(user.getId());
    userResponse.setFirstName(user.getFirstName());
    userResponse.setLastName(user.getLastName());
    userResponse.setEmail(user.getEmail());
    userResponse.setPhoto(user.getPhoto());
<<<<<<< Updated upstream
    userResponse.setRoles(user.getRoles());
    return userResponse;
  }
}
=======
    List<RoleResponse> roles = convertToRoles(user.getRoles());
    userResponse.setRoles(roles);
    return userResponse;
  }
}
>>>>>>> Stashed changes
