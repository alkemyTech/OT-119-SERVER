package com.alkemy.ong.model.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

  private String firstName;
  private String lastName;
  private String email;
  private String photo;
  private List<RoleResponse> roles;

}
