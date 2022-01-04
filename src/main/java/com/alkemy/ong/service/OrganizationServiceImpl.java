package com.alkemy.ong.service;

import com.alkemy.ong.common.EntityUtils;
import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.response.ListSlideResponse;
import com.alkemy.ong.model.response.OrganizationResponse;
import com.alkemy.ong.repository.IOrganizationRepository;
import com.alkemy.ong.service.abstraction.IGetOrganizationService;
import com.alkemy.ong.service.abstraction.IGetSlideService;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements IGetOrganizationService {

  private static final String ORGANIZATION_NOT_FOUND_MESSAGE = "Organization not found.";

  @Autowired
  private IOrganizationRepository organizationRepository;

  @Autowired
  private IGetSlideService slideService;

  @Override
  public Organization getOrganization() {
    List<Organization> organizations = organizationRepository.findAll();
    if (organizations.isEmpty()) {
      throw new EntityNotFoundException(ORGANIZATION_NOT_FOUND_MESSAGE);
    }
    return organizations.get(0);
  }

  @Override
  public OrganizationResponse getOrganizationWithSlides() {
    Organization organization = getOrganization();
    ListSlideResponse slides = slideService.getAll();
    return EntityUtils.convertTo(organization, slides);
  }
}
