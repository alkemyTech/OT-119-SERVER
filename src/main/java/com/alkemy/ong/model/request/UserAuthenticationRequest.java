package com.alkemy.ong.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthenticationRequest {

  @Email(message = "Please input a valid email")
  private String email;
  @Size(min = 8)
  private String password;
}
