package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.response.ListUserResponse;

public interface IGetUserService {

  User getBy(String authorizationHeader);

  ListUserResponse list();

}
