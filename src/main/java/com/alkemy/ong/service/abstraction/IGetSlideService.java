package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.response.SlideResponse;
import java.util.List;
import javax.persistence.EntityNotFoundException;

public interface IGetSlideService {

  List<SlideResponse> getAllSlides() throws EntityNotFoundException;

}
