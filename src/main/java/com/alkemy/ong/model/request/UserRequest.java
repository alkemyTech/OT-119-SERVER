package com.alkemy.ong.model.request;

import com.alkemy.ong.model.entity.Role;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequest {
  @NotEmpty
  @NotBlank
  private String firstname;
  @NotEmpty
  @NotBlank
  private String lastname;
  @Email
  private String email;
  @Size(min = 8)
  private String password;
  private String photo;

}
