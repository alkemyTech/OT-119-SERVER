package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.entity.Role;

public interface IRoleService {

  Role findBy(String name);

}
