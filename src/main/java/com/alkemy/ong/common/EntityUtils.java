package com.alkemy.ong.common;

import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.response.CategoryDetailsResponse;
import com.alkemy.ong.model.response.NewsDetailsResponse;
import com.alkemy.ong.model.response.OrganizationResponse;
import com.alkemy.ong.model.response.UserDetailsResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class EntityUtils {

  private EntityUtils() {
  }

  public static UserDetailsResponse convertTo(User user) {
    UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
    userDetailsResponse.setFirstName(user.getFirstName());
    userDetailsResponse.setLastName(user.getLastName());
    userDetailsResponse.setEmail(user.getEmail());
    return userDetailsResponse;
  }

  public static OrganizationResponse convertTo(Organization organization) {
    OrganizationResponse organizationResponse = new OrganizationResponse();
    BeanUtils.copyProperties(organization, organizationResponse);
    return organizationResponse;
  }

  public static CategoryDetailsResponse convertTo(Category category) {
    CategoryDetailsResponse categoryDetailsResponse = new CategoryDetailsResponse();
    BeanUtils.copyProperties(category, categoryDetailsResponse);
    return categoryDetailsResponse;
  }

  public static NewsDetailsResponse convertTo(News news) {
    NewsDetailsResponse newsDetailsResponse = new NewsDetailsResponse();
    CategoryDetailsResponse categoryDetailsResponse = convertTo(news.getCategory());
    newsDetailsResponse.setCategory(categoryDetailsResponse);
    BeanUtils.copyProperties(news, newsDetailsResponse);
    return newsDetailsResponse;
  }

}
