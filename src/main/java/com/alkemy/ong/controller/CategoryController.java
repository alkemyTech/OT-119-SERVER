package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.CategoryDetailsRequest;
import com.alkemy.ong.model.response.CategoryDetailsResponse;
import com.alkemy.ong.model.response.ListCategoryResponse;
import com.alkemy.ong.service.abstraction.ICreateCategoryService;
import com.alkemy.ong.service.abstraction.IDeleteCategoryService;
import com.alkemy.ong.service.abstraction.IGetCategoryService;
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
public class CategoryController {

  @Autowired
  private IDeleteCategoryService deleteCategoryService;
  @Autowired
  private IGetCategoryService getCategoryService;
  @Autowired
  private ICreateCategoryService createCategoryService;

  @DeleteMapping(value = "/categories/{id}")
  public ResponseEntity<Empty> delete(@PathVariable long id) throws EntityNotFoundException {
    deleteCategoryService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping(value = "/categories/{id}")
  public ResponseEntity<CategoryDetailsResponse> getBy(@PathVariable long id)
      throws EntityNotFoundException {
    return ResponseEntity.status(HttpStatus.OK).body(getCategoryService.findBy(id));
  }

  @PostMapping(value = "/categories")
  public ResponseEntity<CategoryDetailsResponse> create(
      @Valid @RequestBody CategoryDetailsRequest categoryDetailsRequest) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(createCategoryService.create(categoryDetailsRequest));
  }

  @GetMapping(value = "/categories")
  public ResponseEntity<ListCategoryResponse> list() {
    return new ResponseEntity<>(getCategoryService.list(), HttpStatus.OK);
  }

}
