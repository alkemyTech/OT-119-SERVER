package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.response.UserResponse;
import java.util.List;

public interface IGetUserService {

  User getBy(String authorizationHeader);

  List<UserResponse> getListUserResponse();
}
