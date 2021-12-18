package com.alkemy.ong.model.response;

import java.util.List;
import lombok.Getter;

@Getter
public class JwtResponse {

  private String token;
  private String email;
  private List<String> roles;

  public JwtResponse(String accessToken, String email, List<String> roles) {
    this.token = accessToken;
    this.email = email;
    this.roles = roles;
  }

}
