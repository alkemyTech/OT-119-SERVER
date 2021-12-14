package com.alkemy.ong.service.abstraction;

import javax.persistence.EntityNotFoundException;

public interface IDeleteCategoryService {

  void delete(Long id) throws EntityNotFoundException;

}
