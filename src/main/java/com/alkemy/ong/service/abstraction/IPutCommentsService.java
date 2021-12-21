package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.exception.OperationNotAllowedException;

public interface IPutCommentsService {
    void update(Long id, String authorizationHeader) throws OperationNotAllowedException;
}
