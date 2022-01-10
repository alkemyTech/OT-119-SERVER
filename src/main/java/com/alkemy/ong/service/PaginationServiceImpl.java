package com.alkemy.ong.service;

import com.alkemy.ong.service.abstraction.IAddPaginationHeaders;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PaginationServiceImpl implements IAddPaginationHeaders {

  @Override
  public void add(UriComponentsBuilder uriBuilder, int page, int totalPages,
      int pageSize, HttpServletResponse response, String endpoint) {
    uriBuilder.path(endpoint);

    final StringBuilder linkHeader = new StringBuilder();
    if (hasNextPage(page, totalPages)) {
      final String uriForNextPage = constructNextPageUri(uriBuilder, page + 1);
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
