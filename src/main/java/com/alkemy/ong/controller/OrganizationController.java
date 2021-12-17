package com.alkemy.ong.controller;

import com.alkemy.ong.common.OrganizationUtils;
import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.response.OrganizationResponsePublic;
import com.alkemy.ong.service.abstraction.IGetOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

  @Autowired IGetOrganizationService organizationService;

  OrganizationUtils orgUtils = new OrganizationUtils();

  @GetMapping(value = "/public")
  ResponseEntity<OrganizationResponsePublic> getPublicData() {
    Organization org = organizationService.getOrganization();
    return new ResponseEntity<>(orgUtils.convertToDto(org), HttpStatus.OK);
  }
}
