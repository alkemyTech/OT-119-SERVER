package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.entity.User;
<<<<<<< Updated upstream
import com.alkemy.ong.model.response.UserResponse;
import java.util.List;
=======
import com.alkemy.ong.model.response.ListUserResponse;
>>>>>>> Stashed changes

public interface IGetUserService {

  User getBy(String authorizationHeader);

<<<<<<< Updated upstream
  List<UserResponse> getListUserResponse();
=======
  ListUserResponse getList();
>>>>>>> Stashed changes
}
