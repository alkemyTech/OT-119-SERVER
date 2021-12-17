package com.alkemy.ong.service;

import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.repository.IOrganizationRepository;
import com.alkemy.ong.service.abstraction.IGetOrganizationService;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetOrganizationServiceImpl implements IGetOrganizationService {

  private static final String ORGANIZATION_NOT_FOUND_MESSAGE = "Organization not found.";

  @Autowired IOrganizationRepository repository;

  @Override
  public Organization
      getOrganization() { // This method implementation assumes there is only one Organization in
                          // the database.
    List<Organization> orgLst = repository.findAll();
    if (orgLst.isEmpty()) {
      throw new EntityNotFoundException(ORGANIZATION_NOT_FOUND_MESSAGE);
    }
    return orgLst.get(0);
  }
}
