package com.alkemy.ong.service;

import com.alkemy.ong.common.ContactDtoUtils;
import com.alkemy.ong.common.ContactEntityUtils;
import com.alkemy.ong.model.entity.Contact;
import com.alkemy.ong.model.request.ContactSaveRequest;
import com.alkemy.ong.model.response.ContactSaveResponse;
import com.alkemy.ong.repository.IContactRepository;
import com.alkemy.ong.service.abstraction.ISaveContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ISaveContactService {

  @Autowired
  ContactDtoUtils contactDtoUtils;
  @Autowired
  IContactRepository repository;
  @Autowired
  ContactEntityUtils contactEntityUtils;

  @Override
  public ContactSaveResponse save(ContactSaveRequest contactSaveRequest) {
    Contact request = contactDtoUtils.contactDTO2Entity(contactSaveRequest);
    Contact contactSave = repository.save(request);
    ContactSaveResponse response = contactEntityUtils.contactEntity2DTO(contactSave);

    return response;
  }
}
