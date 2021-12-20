package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.exception.UserAlreadyExistException;
import com.alkemy.ong.model.request.UserDetailsRequest;
import com.alkemy.ong.model.response.UserDetailsResponse;

public interface IRegisterUserService {

  UserDetailsResponse register(UserDetailsRequest userDetailsRequest)
      throws UserAlreadyExistException;

}
