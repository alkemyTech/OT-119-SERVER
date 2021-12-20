package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.response.CategoryDetailsResponse;

public interface IGetCategoryService {

  CategoryDetailsResponse findBy(Long id);

}
