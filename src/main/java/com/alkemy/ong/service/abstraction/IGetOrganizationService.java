package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.response.OrganizationResponse;

public interface IGetOrganizationService {

  Organization getOrganization();

  OrganizationResponse getOrganizationWithSlides();

}
