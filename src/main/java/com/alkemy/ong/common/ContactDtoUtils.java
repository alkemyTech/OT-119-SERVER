package com.alkemy.ong.common;

import com.alkemy.ong.model.entity.Contact;
import com.alkemy.ong.model.request.ContactSaveRequest;
import org.springframework.stereotype.Component;

@Component
public class ContactDtoUtils {

  public Contact contactDTO2Entity(ContactSaveRequest request) {
    Contact entity = new Contact();
    entity.setName(request.getName());
    entity.setPhone(request.getPhone());
    entity.setEmail(request.getEmail());
    entity.setMessage(request.getMessage());

    return entity;
  }
}
