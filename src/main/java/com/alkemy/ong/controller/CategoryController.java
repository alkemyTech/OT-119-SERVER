package com.alkemy.ong.controller;

import com.alkemy.ong.common.EntityUtils;
import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.model.response.CategoryDetailsResponse;
import com.alkemy.ong.service.abstraction.IDeleteCategoryService;
import com.alkemy.ong.service.abstraction.IGetCategoryService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import javax.persistence.Entity;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

  @Autowired
  private IDeleteCategoryService deleteCategoryService;
  @Autowired
  private IGetCategoryService categoryService;

  @DeleteMapping(value = "/categories/{id}")
  public ResponseEntity<Empty> delete(@PathVariable long id) throws EntityNotFoundException {
    deleteCategoryService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping(value = "/categories/{id}")
  public ResponseEntity<CategoryDetailsResponse> getCategory(@PathVariable long id)
      throws EntityNotFoundException {
    return ResponseEntity.status(HttpStatus.OK).body(categoryService.findById(id));
  }

}
