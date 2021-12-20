package com.alkemy.ong.service;

import com.alkemy.ong.common.EntityUtils;
import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.model.response.ListSlideResponse;
import com.alkemy.ong.model.response.SlideResponse;
import com.alkemy.ong.repository.ISlideRepository;
import com.alkemy.ong.service.abstraction.IDeleteSlideService;
import com.alkemy.ong.service.abstraction.IGetSlideService;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlideServiceImpl implements IDeleteSlideService, IGetSlideService {

  private static final String SLIDE_NOT_FOUND_MESSAGE = "Slide not found.";

  @Autowired
  private ISlideRepository slideRepository;

  @Override
  public void delete(Long id) throws EntityNotFoundException {
    if (!slideRepository.existsById(id)) {
      throw new EntityNotFoundException(SLIDE_NOT_FOUND_MESSAGE);
    }
    slideRepository.deleteById(id);
  }

  @Override
  public ListSlideResponse getAll() {
    List<Slide> slides = slideRepository.findAll();
    List<SlideResponse> slideResponse = EntityUtils.convertTo(slides);
    ListSlideResponse slideResponses = new ListSlideResponse();
    slideResponses.setSlides(slideResponse);
    return slideResponses;

  }

}

