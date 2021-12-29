package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.ActivityDetailsRequest;
import com.alkemy.ong.model.response.ActivityDetailsResponse;
import com.alkemy.ong.service.ActivityServiceImpl;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivityController {

  @Autowired
  private ActivityServiceImpl activityService;

  @PostMapping("/activities")
  public ResponseEntity<ActivityDetailsResponse> createActivity(@Valid @RequestBody
      ActivityDetailsRequest activityDetailsRequest) {
    ActivityDetailsResponse activityDetailsResponse = activityService.create(
        activityDetailsRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(activityDetailsResponse);
  }
}
