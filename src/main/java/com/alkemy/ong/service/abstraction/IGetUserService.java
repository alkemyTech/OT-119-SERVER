package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.entity.User;

public interface IGetUserService {

  User getBy(String authorizationHeader);

}
