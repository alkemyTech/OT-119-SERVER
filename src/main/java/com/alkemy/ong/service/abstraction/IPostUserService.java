package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.request.UserRequest;
import com.alkemy.ong.model.response.UserResponse;

public interface IPostUserService {

  UserResponse save(UserRequest dto);

}
