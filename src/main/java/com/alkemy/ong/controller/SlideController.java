package com.alkemy.ong.controller;

import com.alkemy.ong.exception.InvalidArgumentException;
import com.alkemy.ong.exception.ThirdPartyException;
import com.alkemy.ong.model.request.SlideRequest;
import com.alkemy.ong.model.response.ListSlideResponse;
import com.alkemy.ong.model.response.SlideResponse;
import com.alkemy.ong.service.abstraction.ICreateSlideService;
import com.alkemy.ong.service.abstraction.IDeleteSlideService;
import com.alkemy.ong.service.abstraction.IGetSlideService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SlideController {

  @Autowired
  private IDeleteSlideService deleteSlideService;
  @Autowired
  private IGetSlideService getSlideService;
  @Autowired
  private ICreateSlideService createSlideService;

  @DeleteMapping(value = "/slides/{id}")
  public ResponseEntity<Empty> delete(@PathVariable("id") long id) throws EntityNotFoundException {
    deleteSlideService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping(value = "/slides")
  public ResponseEntity<ListSlideResponse> list() {
    ListSlideResponse listSlideResponse = getSlideService.getAll();
    return ResponseEntity.ok().body(listSlideResponse);
  }

  @GetMapping(value = "/slides/{id}")
  public ResponseEntity<SlideResponse> getById(@PathVariable Long id) {
    SlideResponse slideDetailsResponse = getSlideService.getBy(id);
    return ResponseEntity.status(HttpStatus.OK).body(slideDetailsResponse);
  }

  @PostMapping(value = "/slides")
  public ResponseEntity<SlideResponse> create(@Valid @RequestBody SlideRequest slideRequest)
      throws ThirdPartyException, InvalidArgumentException {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(createSlideService.create(slideRequest));
  }
}

