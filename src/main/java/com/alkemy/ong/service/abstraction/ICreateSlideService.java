package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.exception.InvalidArgumentException;
import com.alkemy.ong.exception.ThirdPartyException;
import com.alkemy.ong.model.request.SlideRequest;
import com.alkemy.ong.model.response.SlideResponse;

public interface ICreateSlideService {

  SlideResponse create(SlideRequest slide) throws ThirdPartyException, InvalidArgumentException;

}
