package com.alkemy.ong.controller;

import com.alkemy.ong.exception.CommentNotFoundException;
import com.alkemy.ong.exception.OperationNotAllowedException;
import com.alkemy.ong.model.entity.Comment;
import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.service.abstraction.IDeleteCommentsService;
import com.alkemy.ong.service.abstraction.IGetCommentsService;
import com.alkemy.ong.service.abstraction.IPostCommentsService;
import com.alkemy.ong.service.abstraction.IPutCommentsService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class CommentController {

  @Autowired
  private IDeleteCommentsService deleteCommentsService;

  @Autowired
  private IPostCommentsService postCommentsService;

  @Autowired
  private IGetCommentsService getCommentsService;

  @Autowired
  private IPutCommentsService putCommentsService;

  @DeleteMapping(value = "/comments/{id}")
  public ResponseEntity<Empty> delete(@PathVariable("id") long id,
      @RequestHeader(value = "Authorization") String authorizationHeader)
      throws OperationNotAllowedException {
    deleteCommentsService.delete(id, authorizationHeader);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PostMapping(value = "/comments/{id}")
  public ResponseEntity<Empty> postComment(@PathVariable("id") long user_id,
                                           @RequestHeader(value = "Authorization") String authorizationHeader,
                                           @RequestBody Comment comment) {
    User user = new User();
    user.setId(user_id);
    comment.setUserId(user);

    try {
      postCommentsService.add(comment, authorizationHeader);
      return new ResponseEntity<>(HttpStatus.OK);
    }catch (OperationNotAllowedException exception){
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
  }

  @GetMapping(value = "/comments")
  public ResponseEntity<Object> getAllComments(@RequestHeader(value = "Authorization") String authorizationHeader){
    try {
      List<Comment> comments = getCommentsService.getComments(authorizationHeader);
      return new ResponseEntity<>(comments,HttpStatus.OK);
    }catch (OperationNotAllowedException exception){
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
  }

  @PutMapping(value = "/comments/{id}")
  public ResponseEntity<Empty> updateComment(@PathVariable("id") Long user_id,
                                             @RequestHeader(value = "Authorization") String authorizationHeader,
                                             @RequestBody Comment comment){
    try {
      putCommentsService.update(user_id,comment,authorizationHeader);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (OperationNotAllowedException exception){
      return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    } catch (CommentNotFoundException exception){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
