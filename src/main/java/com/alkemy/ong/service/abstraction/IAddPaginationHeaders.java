package com.alkemy.ong.service.abstraction;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.util.UriComponentsBuilder;

public interface IAddPaginationHeaders {

  void add(UriComponentsBuilder uriBuilder, int page, int totalPages,
      int pageSize, HttpServletResponse response, String endpoint);

}
