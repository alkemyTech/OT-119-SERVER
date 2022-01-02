package com.alkemy.ong.service;

import com.alkemy.ong.common.EntityUtils;
import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.model.response.ListSlideResponse;
import com.alkemy.ong.model.response.SlideDetailsResponse;
import com.alkemy.ong.repository.ISlideRepository;
import com.alkemy.ong.service.abstraction.IDeleteSlideService;
import com.alkemy.ong.service.abstraction.IGetByIdSlideService;
import com.alkemy.ong.service.abstraction.IGetSlideService;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlideServiceImpl implements IDeleteSlideService, IGetSlideService,
    IGetByIdSlideService {

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
    ListSlideResponse slideResponses = new ListSlideResponse();
    slideResponses.setSlides(EntityUtils.convertTo(slides));
    return slideResponses;
  }

  @Override
  public SlideDetailsResponse getById(Long Id) {
    Optional<Slide> slideOptional = slideRepository.findById(Id);
    if (slideOptional.isEmpty()) {
      throw new EntityNotFoundException(SLIDE_NOT_FOUND_MESSAGE);
    }
    return EntityUtils.converToSlideDetailsResponse(slideOptional.get());
  }
}

