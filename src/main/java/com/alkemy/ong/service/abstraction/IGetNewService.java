package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.entity.New;
import com.alkemy.ong.model.response.NewDetailsResponse;
import java.util.List;

public interface IGetNewService {

  NewDetailsResponse getDetailsById(Long id);
}
