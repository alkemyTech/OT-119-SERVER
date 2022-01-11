package com.alkemy.ong.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAuthenticatedResponse {

  private String token;
  private String email;

  public UserAuthenticatedResponse(String token, String email) {
    this.token = token;
    this.email = email;
  }

}
