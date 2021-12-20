package com.alkemy.ong.controller;

import com.alkemy.ong.model.response.NewDetailsResponse;
import com.alkemy.ong.service.abstraction.IGetNewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("news")
public class NewController {

  @Autowired
  IGetNewService getNewService;

  @GetMapping("/{id}")
  public ResponseEntity<NewDetailsResponse> getDetailsById(@PathVariable Long id) {
    NewDetailsResponse newDetails = getNewService.getDetailsById(id);
    return ResponseEntity.status(HttpStatus.OK).body(newDetails);
  }

}
