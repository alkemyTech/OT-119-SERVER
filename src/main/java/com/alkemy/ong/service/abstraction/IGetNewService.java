package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.response.NewDetailsResponse;

public interface IGetNewService {

  NewDetailsResponse getDetailsById(Long id);
}
