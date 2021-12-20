package com.alkemy.ong.controller;

import com.alkemy.ong.exception.OperationNotAllowedException;
import com.alkemy.ong.model.entity.Comment;
import com.alkemy.ong.service.abstraction.IDeleteCommentsService;
import com.alkemy.ong.service.abstraction.IPostCommentsService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CommentController {

  @Autowired
  private IDeleteCommentsService deleteCommentsService;

  @Autowired
  private IPostCommentsService postCommentsService;

  @DeleteMapping(value = "/comments/{id}")
  public ResponseEntity<Empty> delete(@PathVariable("id") long id,
      @RequestHeader(value = "Authorization") String authorizationHeader)
      throws OperationNotAllowedException {
    deleteCommentsService.delete(id, authorizationHeader);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PostMapping(value = "/comments/{id}")
  public ResponseEntity<Empty> post(@PathVariable("id") long id,
                                    @RequestHeader(value = "Authorization") String authorizationHeader,
                                    @Valid @RequestBody Comment comment)
          throws OperationNotAllowedException {
    postCommentsService.add(id, comment, authorizationHeader);
    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
  }

}
