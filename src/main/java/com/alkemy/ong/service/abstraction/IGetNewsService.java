package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.response.NewsDetailsResponse;

public interface IGetNewsService {

  NewsDetailsResponse getById(Long id);

}
