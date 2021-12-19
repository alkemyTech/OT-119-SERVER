package com.alkemy.ong.service.abstraction;

import javax.persistence.EntityNotFoundException;

public interface IDeleteNewsService {

  void delete(Long id) throws EntityNotFoundException;
}
