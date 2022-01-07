package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.exception.CustomException;
import com.alkemy.ong.model.request.SlideRequest;
import com.alkemy.ong.model.response.SlideResponse;

public interface ICreateSlideService {
  SlideResponse create (SlideRequest slide) throws CustomException;

}
