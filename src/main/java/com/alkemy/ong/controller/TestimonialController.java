package com.alkemy.ong.controller;

import com.alkemy.ong.service.abstraction.IDeleteTestimonialService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestimonialController {

  @Autowired
  private IDeleteTestimonialService deleteTestimonialService;

  @DeleteMapping(value = "/testimonials/{id}")
  public ResponseEntity<Empty> delete(@PathVariable Long id) throws EntityNotFoundException {
    deleteTestimonialService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
