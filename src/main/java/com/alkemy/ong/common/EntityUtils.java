package com.alkemy.ong.common;

import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.response.OrganizationResponse;
import org.springframework.beans.BeanUtils;

public class EntityUtils {

  private EntityUtils() {
  }

  public static OrganizationResponse convertTo(Organization organization) {
    OrganizationResponse organizationResponse = new OrganizationResponse();
    BeanUtils.copyProperties(organization, organizationResponse);
    return organizationResponse;
  }
}
