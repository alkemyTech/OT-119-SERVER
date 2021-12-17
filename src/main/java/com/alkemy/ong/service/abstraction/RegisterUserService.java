package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.exception.InvalidCredentialsException;
import com.alkemy.ong.model.request.UserDetailsRequest;
import com.alkemy.ong.model.response.UserDetailsResponse;

public interface RegisterUserService {

  UserDetailsResponse register(UserDetailsRequest userDetailsRequest) throws InvalidCredentialsException;

}
