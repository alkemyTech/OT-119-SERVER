package com.alkemy.ong.service;

import com.alkemy.ong.model.entity.Testimonial;
import com.alkemy.ong.repository.ITestimonialRepository;
import com.alkemy.ong.service.abstraction.IDeleteTestimonialService;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestimonialServiceImpl implements IDeleteTestimonialService {

  private static final String TESTIMONIAL_NOT_FOUND_MESSAGE = "Testimonial not found.";

  @Autowired
  private ITestimonialRepository testimonialRepository;

  @Override
  public void delete(Long id) throws EntityNotFoundException {
    Testimonial testimonial = getTestimonial(id);
    testimonial.setSoftDelete(true);
    testimonialRepository.save(testimonial);
  }

  private Testimonial getTestimonial(Long id) {
    Optional<Testimonial> testimonialOptional = testimonialRepository.findById(id);
    if (testimonialOptional.isEmpty() || testimonialOptional.get().isSoftDelete()) {
      throw new EntityNotFoundException(TESTIMONIAL_NOT_FOUND_MESSAGE);
    }
    return testimonialOptional.get();
  }

}
