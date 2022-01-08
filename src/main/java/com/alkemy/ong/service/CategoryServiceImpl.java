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
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

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

  @Override
  public Page<Category> findPaginated(Pageable pageable) {
    return categoryRepository.findAll(pageable);
  }

  @Override
  public void addLinksToHeader(UriComponentsBuilder uriBuilder, int page, int totalPages,
      int pageSize, HttpServletResponse response) {
    uriBuilder.path("/categories");

    final StringBuilder linkHeader = new StringBuilder();
    if (hasNextPage(page, totalPages)) {
      final String uriForNextPage = constructNextPageUri(uriBuilder, page);
      linkHeader.append(createLinkHeader(uriForNextPage, "next"));
    }
    if (hasPreviousPage(page)) {
      final String uriForPrevPage = constructPrevPageUri(uriBuilder, page);
      appendCommaIfNecessary(linkHeader);
      linkHeader.append(createLinkHeader(uriForPrevPage, "prev"));
    }
    response.addHeader("Link", linkHeader.toString());
  }

  String constructNextPageUri(final UriComponentsBuilder uriBuilder, final int page) {
    return uriBuilder.replaceQueryParam("page", page).build()
        .encode().toUriString();
  }

  String constructPrevPageUri(final UriComponentsBuilder uriBuilder, final int page) {
    return uriBuilder.replaceQueryParam("page", page - 1).build()
        .encode().toUriString();
  }

  boolean hasNextPage(final int page, final int totalPages) {
    return page < totalPages - 1;
  }

  boolean hasPreviousPage(final int page) {
    return page > 0;
  }

  void appendCommaIfNecessary(final StringBuilder linkHeader) {
    if (linkHeader.length() > 0) {
      linkHeader.append(", ");
    }
  }

  public static String createLinkHeader(final String uri, final String rel) {
    return "<" + uri + ">; rel=\"" + rel + "\"";
  }
}
