package com.alkemy.ong.service;

import com.alkemy.ong.common.EntityUtils;
import com.alkemy.ong.model.entity.New;
import com.alkemy.ong.model.response.NewDetailsResponse;
import com.alkemy.ong.repository.INewRepository;
import com.alkemy.ong.service.abstraction.IGetNewService;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewServiceImpl implements IGetNewService {

  private static final String NEW_NOT_FOUND_MESSAGE = "New not found.";

  @Autowired
  INewRepository newRepository;

  @Override
  public NewDetailsResponse getDetailsById(Long id) {

    Optional<New> newDetails = newRepository.findById(id);
    if (!newDetails.isPresent()) {
      throw new EntityNotFoundException(NEW_NOT_FOUND_MESSAGE);
    }
    NewDetailsResponse newDetailsResponse = EntityUtils.convertTo(newDetails.get());
    return newDetailsResponse;
  }
}
