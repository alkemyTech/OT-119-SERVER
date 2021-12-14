package com.alkemy.ong.service.abstraction;

import javax.persistence.EntityNotFoundException;

public interface IDeleteSlideService {

  void delete(Long id) throws EntityNotFoundException;

}
