package com.alkemy.ong.common;

import com.alkemy.ong.model.entity.Contact;
import com.alkemy.ong.model.response.ContactSaveResponse;
import org.springframework.stereotype.Component;

@Component
public class ContactEntityUtils {

  public ContactSaveResponse contactEntity2DTO(Contact entity) {
    ContactSaveResponse response = new ContactSaveResponse();
    response.setName(entity.getName());
    response.setPhone(entity.getPhone());
    response.setEmail(entity.getEmail());
    response.setMessage(entity.getMessage());

    return response;
  }
}
