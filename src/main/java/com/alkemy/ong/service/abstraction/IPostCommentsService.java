package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.exception.OperationNotAllowedException;
import com.alkemy.ong.model.entity.Comment;

public interface IPostCommentsService {

    void add(Long id, Comment comment, String authorizationHeader) throws OperationNotAllowedException;
}
