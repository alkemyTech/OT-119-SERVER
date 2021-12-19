package com.alkemy.ong.service;

import com.alkemy.ong.common.SlideUtil;
import com.alkemy.ong.model.entity.Slide;
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
  public List<SlideResponse> getAllSlides() throws EntityNotFoundException {
    List<Slide> entities = slideRepository.findAll();
    if (entities.isEmpty()) {
      throw new EntityNotFoundException(SLIDE_NOT_FOUND_MESSAGE);
    }
    return SlideUtil.SlideEntityList2ResponseList(entities);
  }

}

