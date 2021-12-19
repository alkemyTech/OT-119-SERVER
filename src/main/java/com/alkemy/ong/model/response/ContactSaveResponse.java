package com.alkemy.ong.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactSaveResponse {

  private String name;
  private String phone;
  private String email;
  private String message;
}