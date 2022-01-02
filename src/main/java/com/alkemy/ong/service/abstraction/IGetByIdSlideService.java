package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.response.SlideDetailsResponse;

public interface IGetByIdSlideService {

  SlideDetailsResponse getById(Long Id);

}
