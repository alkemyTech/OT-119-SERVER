package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.UserAuthenticationRequest;
import org.springframework.security.core.Authentication;

public interface IGetUserService {

  User getBy(String authorizationHeader);

  Authentication authentication(UserAuthenticationRequest authRequest);

}
