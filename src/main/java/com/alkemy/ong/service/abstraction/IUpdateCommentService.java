package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.request.CommentRequest;
import com.alkemy.ong.model.response.CommentResponse;

public interface IUpdateCommentService {

  CommentResponse update(CommentRequest commentRequest, long id, String authorizationHeader);

}
