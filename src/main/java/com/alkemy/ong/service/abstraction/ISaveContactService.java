package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.request.ContactSaveRequest;
import com.alkemy.ong.model.response.ContactSaveResponse;

public interface ISaveContactService {

  ContactSaveResponse save(ContactSaveRequest contactSaveRequest);
}
