package com.alkemy.ong.service;


import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.repository.INewsRepository;
import com.alkemy.ong.service.abstraction.IDeleteNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class NewsServiceImpl implements IDeleteNewsService {

  private static final String NEWS_NOT_FOUND_MESSAGE = "News not found.";

  @Autowired
  private INewsRepository newsRepository;

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
