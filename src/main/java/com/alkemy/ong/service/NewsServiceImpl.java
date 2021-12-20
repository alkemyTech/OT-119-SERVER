package com.alkemy.ong.service;

import com.alkemy.ong.common.EntityUtils;
import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.model.response.NewsDetailsResponse;
import com.alkemy.ong.repository.INewsRepository;
import com.alkemy.ong.service.abstraction.IGetNewsService;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements IGetNewsService {

  private static final String NEW_NOT_FOUND_MESSAGE = "New not found.";

  @Autowired
  private INewsRepository newsRepository;

  @Override
  public NewsDetailsResponse getById(Long id) {
    Optional<News> newsDetails = newsRepository.findById(id);
    if (newsDetails.isEmpty() || newsDetails.get().isSoftDelete()) {
      throw new EntityNotFoundException(NEW_NOT_FOUND_MESSAGE);
    }
    return EntityUtils.convertTo(newsDetails.get());
  }
}
