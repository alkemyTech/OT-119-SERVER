package com.alkemy.ong.service.abstraction;

import javax.persistence.EntityNotFoundException;

public interface IDeleteMembersService {

  void delete(Long id) throws EntityNotFoundException;

}
