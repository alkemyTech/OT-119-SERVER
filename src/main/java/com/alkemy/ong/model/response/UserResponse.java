package com.alkemy.ong.model.response;

import com.alkemy.ong.model.entity.Role;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
<<<<<<< Updated upstream
@JsonRootName(value = "Users")
=======
@JsonRootName(value = "User")
>>>>>>> Stashed changes
public class UserResponse {

  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String photo;
<<<<<<< Updated upstream
  private List<Role> roles;
=======
  private List<RoleResponse> roles;
>>>>>>> Stashed changes



}