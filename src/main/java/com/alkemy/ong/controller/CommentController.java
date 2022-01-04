package com.alkemy.ong.controller;

import com.alkemy.ong.exception.OperationNotAllowedException;
import com.alkemy.ong.model.request.CommentRequest;
import com.alkemy.ong.model.response.CommentResponse;
import com.alkemy.ong.service.abstraction.IDeleteCommentsService;
import com.alkemy.ong.service.abstraction.IUpdateCommentService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

  @Autowired
  private IDeleteCommentsService deleteCommentsService;
  @Autowired
  private IUpdateCommentService updateCommentService;

  @DeleteMapping(value = "/comments/{id}")
  public ResponseEntity<Empty> delete(@PathVariable("id") long id,
      @RequestHeader(value = "Authorization") String authorizationHeader)
      throws OperationNotAllowedException {
    deleteCommentsService.delete(id, authorizationHeader);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping(value = "/comments/{id}")
  public ResponseEntity<CommentResponse> update(@PathVariable("id") long id,
      @RequestBody CommentRequest commentRequest,
      @RequestHeader(value = "Authorization") String authorizationHeader) {
    CommentResponse commentResponse = updateCommentService.update(commentRequest,
        id,
        authorizationHeader);
    return new ResponseEntity<>(commentResponse, HttpStatus.OK);
  }

}
