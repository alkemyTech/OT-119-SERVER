package com.alkemy.ong.service.abstraction;

public interface IUpdateCommentService {

  void updateComment(String body, long id, String authorizationHeader);

}
