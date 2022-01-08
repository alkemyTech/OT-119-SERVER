package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.model.response.CategoryDetailsResponse;
import com.alkemy.ong.model.response.ListCategoryResponse;
import javax.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriComponentsBuilder;

public interface IGetCategoryService {

  CategoryDetailsResponse findBy(Long id);

  ListCategoryResponse list();

  Page<Category> findPaginated(Pageable pageable);

  void addLinksToHeader(UriComponentsBuilder uriBuilder, int page, int totalPages, int pageSize,
      HttpServletResponse response);

}
