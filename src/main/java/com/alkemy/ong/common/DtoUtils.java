package com.alkemy.ong.common;

import com.alkemy.ong.model.entity.Activity;
import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.ActivityDetailsRequest;
import com.alkemy.ong.model.request.CategoryDetailsRequest;
import com.alkemy.ong.model.request.UserDetailsRequest;

public class DtoUtils {

  private DtoUtils() {
  }

  public static User convertTo(UserDetailsRequest userDetailsRequest) {
    User user = new User();
    user.setFirstName(userDetailsRequest.getFirstName());
    user.setLastName(userDetailsRequest.getLastName());
    user.setEmail(userDetailsRequest.getEmail());
    return user;
  }

  public static Category convertTo(CategoryDetailsRequest categoryDetailsRequest) {
    Category category = new Category();
    category.setName(categoryDetailsRequest.getName());
    category.setDescription(categoryDetailsRequest.getDescription());
    category.setImage(categoryDetailsRequest.getImage());
    return category;
  }

  public static Activity convertTo(ActivityDetailsRequest activityDetailsRequest) {
    Activity activity = new Activity();
    activity.setName(activityDetailsRequest.getName());
    activity.setContent(activityDetailsRequest.getContent());
    activity.setImage(activityDetailsRequest.getImage());
    return activity;
  }

}
