package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.response.CategoryDetailsResponse;
import com.alkemy.ong.model.response.ListCategoryResponse;

public interface IGetCategoryService {

  CategoryDetailsResponse findBy(Long id);

  ListCategoryResponse list();

}
