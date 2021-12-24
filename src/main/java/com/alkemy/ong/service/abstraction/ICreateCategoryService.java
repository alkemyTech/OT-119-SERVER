package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.request.CategoryDetailsRequest;
import com.alkemy.ong.model.response.CategoryDetailsResponse;

public interface ICreateCategoryService {

  CategoryDetailsResponse create(CategoryDetailsRequest categoryDetailsRequest);

}
