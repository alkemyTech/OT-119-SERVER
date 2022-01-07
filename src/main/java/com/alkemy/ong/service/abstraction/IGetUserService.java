package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.response.ListUserResponse;
import com.alkemy.ong.model.response.UserResponse;

public interface IGetUserService {

  User getBy(String authorizationHeader);

  ListUserResponse list();

  UserResponse getUserBy(String authorizationHeader);
}
