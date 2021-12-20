package com.alkemy.ong.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsRequest {

  @NotBlank(message = "firstName cannot be blank.")
  private String firstName;

  @NotBlank(message = "lastName cannot be blank.")
  private String lastName;

  @Email(message = "Email should have a valid format.")
  @NotBlank(message = "Email cannot be empty.")
  private String email;

  @Size(min = 8, message = "Password must be contain at least 8 characters.")
  private String password;

}
