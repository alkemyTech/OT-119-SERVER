package com.alkemy.ong.service.abstraction;

import javax.persistence.EntityNotFoundException;

public interface IDeleteUserService {

  void delete(Long id) throws EntityNotFoundException;

}
