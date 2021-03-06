package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.response.ListSlideResponse;
import com.alkemy.ong.model.response.SlideResponse;

public interface IGetSlideService {

  ListSlideResponse getAll();

  SlideResponse getBy(Long id);
}
