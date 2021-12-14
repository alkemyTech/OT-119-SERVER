package com.alkemy.ong.controller;

import com.alkemy.ong.service.abstraction.IDeleteSlideService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SlideController {

  @Autowired
  private IDeleteSlideService deleteSlideService;

  @DeleteMapping(value = "/slides/{id}")
  public ResponseEntity<Empty> delete(@PathVariable("id") long id) throws EntityNotFoundException {
    deleteSlideService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}

