package com.alkemy.ong.model.response;

import java.util.List;
import lombok.Getter;

@Getter
public class UserAuthenticatedResponse {

  private String token;
  private String email;

  public UserAuthenticatedResponse(String accessToken, String email) {
    this.token = accessToken;
    this.email = email;
  }

}
