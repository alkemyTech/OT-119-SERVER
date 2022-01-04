package com.alkemy.ong.controller;

import com.alkemy.ong.common.EntityUtils;
import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.response.ListSlideResponse;
import com.alkemy.ong.model.response.OrganizationResponse;
import com.alkemy.ong.service.abstraction.IGetOrganizationService;
import com.alkemy.ong.service.abstraction.IGetSlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

  @Autowired
  private IGetOrganizationService organizationService;
  @Autowired
  private IGetSlideService slideService;

  @GetMapping(value = "/public")
  public ResponseEntity<OrganizationResponse> getOrganizationPublicDetails() {
    Organization organization = organizationService.getOrganization();
    ListSlideResponse slides = slideService.getAll();
    return new ResponseEntity<>(EntityUtils.convertTo(organization, slides), HttpStatus.OK);
  }

}
