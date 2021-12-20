package com.alkemy.ong.service;

import com.alkemy.ong.common.EntityUtils;
import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.model.response.NewsDetailsResponse;
import com.alkemy.ong.repository.INewsRepository;
import com.alkemy.ong.service.abstraction.IDeleteNewsService;
import com.alkemy.ong.service.abstraction.IGetNewsService;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements IGetNewsService, IDeleteNewsService {

  private static final String NEWS_NOT_FOUND_MESSAGE = "News not found.";

  @Autowired
  private INewsRepository newsRepository;

  @Override
  public NewsDetailsResponse getById(Long id) {
    News newsDetails = getNews(id);
    return EntityUtils.convertTo(newsDetails);
  }

  @Override
  public void delete(Long id) throws EntityNotFoundException {
    News news = getNews(id);
    news.setSoftDelete(true);
    newsRepository.save(news);
  }

  private News getNews(Long id) {
    Optional<News> newsOptional = newsRepository.findById(id);
    if (newsOptional.isEmpty() || newsOptional.get().isSoftDelete()) {
      throw new EntityNotFoundException(NEWS_NOT_FOUND_MESSAGE);
    }
    return newsOptional.get();
  }

}
