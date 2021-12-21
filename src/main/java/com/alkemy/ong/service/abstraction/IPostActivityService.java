package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.exception.OperationNotAllowedException;

public interface IPostActivityService {

    void add(String name, String content, String authorizationHeader) throws OperationNotAllowedException;
}
