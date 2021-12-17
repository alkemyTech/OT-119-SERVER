package com.alkemy.ong.common;

import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.response.OrganizationResponsePublic;
import org.springframework.beans.BeanUtils;

public class OrganizationUtils {

  public OrganizationResponsePublic convertToDto(Organization org) {
    OrganizationResponsePublic orgDto = new OrganizationResponsePublic();
    BeanUtils.copyProperties(org, orgDto);
    return orgDto;
  }
}
