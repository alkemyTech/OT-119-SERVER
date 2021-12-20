package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.request.UserAuthenticationRequest;
import com.alkemy.ong.model.response.UserAuthenticatedResponse;

public interface IAuthenticationService {

  UserAuthenticatedResponse authentication(UserAuthenticationRequest authRequest);

}
