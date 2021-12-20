package com.alkemy.ong.common;

import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.response.CategoryDetailsResponse;
import com.alkemy.ong.model.response.NewDetailsResponse;
import com.alkemy.ong.model.response.OrganizationResponse;
import org.springframework.beans.BeanUtils;

public class EntityUtils {

  private EntityUtils() {
  }

  public static OrganizationResponse convertTo(Organization organization) {
    OrganizationResponse organizationResponse = new OrganizationResponse();
    BeanUtils.copyProperties(organization, organizationResponse);
    return organizationResponse;
  }

  public static CategoryDetailsResponse convertTo(Category categoryEntity) {
    CategoryDetailsResponse categoryDetailsResponse = new CategoryDetailsResponse();
    BeanUtils.copyProperties(categoryEntity, categoryDetailsResponse);
    return categoryDetailsResponse;
  }

  public static NewDetailsResponse convertTo(News newEntity) {
    NewDetailsResponse newDetailsResponse = new NewDetailsResponse();
    CategoryDetailsResponse categoryDetailsResponse = convertTo(newEntity.getCategory());
    newDetailsResponse.setCategory(categoryDetailsResponse);
    BeanUtils.copyProperties(newEntity, newDetailsResponse);
    return newDetailsResponse;
  }

}
