package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.exception.OperationNotAllowedException;
import com.alkemy.ong.model.entity.Comment;

public interface IPutCommentsService {
    void update(Long id, Comment comment, String authorizationHeader) throws OperationNotAllowedException;
}
