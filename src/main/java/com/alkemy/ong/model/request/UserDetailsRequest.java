package com.alkemy.ong.model.request;

import com.alkemy.ong.model.entity.Role;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDetailsRequest {

  private static final String EMPTY_FIELD = "the field cannot be empty";
  private static final String BLANK_FIELD = "the field cannot be blank";

  @NotEmpty(message = EMPTY_FIELD)
  @NotBlank(message = BLANK_FIELD)
  private String firstName;
  @NotEmpty(message = EMPTY_FIELD)
  @NotBlank(message = BLANK_FIELD)
  private String lastName;
  @Email(message = "Enter a valid Email")
  private String email;
  @Size(min = 8, message = "Password must be contain at least 8 characters")
  private String password;
  private List<Role> roles;
}
