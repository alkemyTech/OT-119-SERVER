package com.alkemy.ong.common;

import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.response.OrganizationResponse;
import com.alkemy.ong.model.response.UserDetailsResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.response.OrganizationResponse;
import org.springframework.beans.BeanUtils;

@Service
public class EntityUtils {

  private EntityUtils() {
  }

  public static UserDetailsResponse convertTo(User userEntity) {
    UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
    userDetailsResponse.setFirstName(userEntity.getFirstName());
    userDetailsResponse.setLastName(userEntity.getLastName());
    userDetailsResponse.setEmail(userEntity.getEmail());
    return userDetailsResponse;
  }

  public static OrganizationResponse convertTo(Organization organization) {
    OrganizationResponse organizationResponse = new OrganizationResponse();
    BeanUtils.copyProperties(organization, organizationResponse);
    return organizationResponse;
  }

}
