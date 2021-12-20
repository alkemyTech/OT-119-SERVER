package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.exception.OperationNotAllowedException;
import com.alkemy.ong.model.entity.Comment;

import java.util.List;

public interface IGetCommentsService {

    List<Comment> getComments(String authorizationHeader) throws OperationNotAllowedException;

}
