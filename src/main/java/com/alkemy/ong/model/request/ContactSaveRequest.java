package com.alkemy.ong.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactSaveRequest {

  private Long id;
  @NotBlank(message = "Enter a Name")
  private String name;
  @NotBlank(message = "Enter a Phone")
  private String phone;
  @Email(message = "Enter a valid Email")
  private String email;
  @NotBlank(message = "Enter a Message")
  private String message;
}
