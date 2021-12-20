package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.entity.Role;
import java.util.List;

public interface IRoleService {

  Role findBy(String name);

  List<Role> findAllByIds(List<Long> ids);
}
