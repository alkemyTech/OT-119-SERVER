package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.response.ListCommentsResponse;


public interface IGetCommentService {

  ListCommentsResponse getComments(Long id);

}
