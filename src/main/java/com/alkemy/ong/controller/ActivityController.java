package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.ActivityDetailsRequest;
import com.alkemy.ong.model.response.ActivityDetailsResponse;
import com.alkemy.ong.service.abstraction.ICreateActivityService;
import com.alkemy.ong.service.abstraction.IUpdateActivityService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivityController {

  @Autowired
  private ICreateActivityService createActivityService;
  @Autowired
  private IUpdateActivityService updateActivityService;

  @PostMapping("/activities")
  public ResponseEntity<ActivityDetailsResponse> create(@Valid @RequestBody
      ActivityDetailsRequest activityDetailsRequest) {
    ActivityDetailsResponse activityDetailsResponse = createActivityService.create(
        activityDetailsRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(activityDetailsResponse);
  }

  @PutMapping("/activities/{id}")
  private ResponseEntity<ActivityDetailsResponse> update(@PathVariable("id") Long id,
      @RequestBody ActivityDetailsRequest activityDetailsRequest) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(updateActivityService.update(id, activityDetailsRequest));
  }
}
