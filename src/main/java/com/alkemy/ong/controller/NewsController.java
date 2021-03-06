package com.alkemy.ong.controller;

import com.alkemy.ong.model.response.ListCommentsResponse;
import com.alkemy.ong.model.response.NewsDetailsResponse;
import com.alkemy.ong.service.abstraction.IDeleteNewsService;
import com.alkemy.ong.service.abstraction.IGetCommentService;
import com.alkemy.ong.service.abstraction.IGetNewsService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsController {

  @Autowired
  private IGetNewsService getNewsService;

  @Autowired
  private IDeleteNewsService deleteNewsService;

  @Autowired
  private IGetCommentService getCommentService;

  @GetMapping("/news/{id}")
  public ResponseEntity<NewsDetailsResponse> getDetailsById(@PathVariable Long id) {
    NewsDetailsResponse newDetails = getNewsService.getById(id);
    return ResponseEntity.status(HttpStatus.OK).body(newDetails);
  }

  @GetMapping("/news/{id}/comments")
  public ResponseEntity<ListCommentsResponse> getCommentsById(@PathVariable Long id) {
    ListCommentsResponse comments = getCommentService.getComments(id);
    return ResponseEntity.status(HttpStatus.OK).body(comments);
  }

  @DeleteMapping(value = "/news/{id}")
  public ResponseEntity<Empty> delete(@PathVariable long id) throws EntityNotFoundException {
    deleteNewsService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
