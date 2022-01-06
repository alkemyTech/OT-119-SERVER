package com.alkemy.ong.controller;

import com.alkemy.ong.model.response.OrganizationResponse;
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

  @Autowired
  private IGetOrganizationService organizationService;

  @GetMapping(value = "/public")
  public ResponseEntity<OrganizationResponse> getOrganizationPublicDetails() {
    return new ResponseEntity<>(organizationService.getOrganizationWithSlides(), HttpStatus.OK);
  }

}
