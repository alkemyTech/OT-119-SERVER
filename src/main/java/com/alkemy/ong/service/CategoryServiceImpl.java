package com.alkemy.ong.service;

import com.alkemy.ong.common.DtoUtils;
import com.alkemy.ong.common.EntityUtils;
import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.model.request.CategoryDetailsRequest;
import com.alkemy.ong.model.response.CategoryDetailsResponse;
import com.alkemy.ong.model.response.ListCategoryResponse;
import com.alkemy.ong.repository.ICategoryRepository;
import com.alkemy.ong.service.abstraction.ICreateCategoryService;
import com.alkemy.ong.service.abstraction.IDeleteCategoryService;
import com.alkemy.ong.service.abstraction.IGetCategoryService;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements IDeleteCategoryService, IGetCategoryService,
    ICreateCategoryService {

  private static final String CATEGORY_NOT_FOUND_MESSAGE = "Category not found.";

  @Autowired
  private ICategoryRepository categoryRepository;

  @Override
  public void delete(Long id) throws EntityNotFoundException {
    Category category = getCategory(id);
    category.setSoftDelete(true);
    categoryRepository.save(category);
  }

  @Override
  public CategoryDetailsResponse findBy(Long id) {
    Category category = getCategory(id);
    return EntityUtils.convertTo(category);
  }

  @Override
  public CategoryDetailsResponse create(CategoryDetailsRequest categoryDetailsRequest) {
    Category category = DtoUtils.convertTo(categoryDetailsRequest);
    category.setSoftDelete(false);
    categoryRepository.save(category);
    return EntityUtils.convertTo(category);
  }

  private Category getCategory(Long id) {
    Optional<Category> categoryOptional = categoryRepository.findById(id);
    if (categoryOptional.isEmpty() || categoryOptional.get().isSoftDelete()) {
      throw new EntityNotFoundException(CATEGORY_NOT_FOUND_MESSAGE);
    }
    return categoryOptional.get();
  }

  @Override
  public ListCategoryResponse list() {
    List<Category> categories = categoryRepository.findAll();
    return EntityUtils.convertToListCategoryResponse(categories);
  }
}
