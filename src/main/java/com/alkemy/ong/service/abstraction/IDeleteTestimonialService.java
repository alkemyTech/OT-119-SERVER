package com.alkemy.ong.service.abstraction;

import javax.persistence.EntityNotFoundException;

public interface IDeleteTestimonialService {

  void delete(Long id) throws EntityNotFoundException;
}
