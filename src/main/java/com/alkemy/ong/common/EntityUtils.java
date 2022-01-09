package com.alkemy.ong.common;

import com.alkemy.ong.model.entity.Activity;
import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.model.entity.Comment;
import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.entity.Role;
import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.response.ActivityDetailsResponse;
import com.alkemy.ong.model.response.CategoryDetailsResponse;
import com.alkemy.ong.model.response.CommentResponse;
import com.alkemy.ong.model.response.ListCategoryResponse;
import com.alkemy.ong.model.response.ListCommentsResponse;
import com.alkemy.ong.model.response.ListSlideResponse;
import com.alkemy.ong.model.response.ListUserResponse;
import com.alkemy.ong.model.response.NewsDetailsResponse;
import com.alkemy.ong.model.response.OrganizationResponse;
import com.alkemy.ong.model.response.RoleResponse;
import com.alkemy.ong.model.response.SlideResponse;
import com.alkemy.ong.model.response.UserDetailsResponse;
import com.alkemy.ong.model.response.UserResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.BeanUtils;

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

  public static OrganizationResponse convertTo(Organization organization,
      ListSlideResponse slideResponse) {
    OrganizationResponse organizationResponse = new OrganizationResponse();
    organizationResponse.setImage(organization.getImage());
    organizationResponse.setAddress(organization.getAddress());
    organizationResponse.setPhone(organization.getPhone());
    organizationResponse.setEmail(organization.getEmail());
    slideResponse.getSlides().sort(Comparator.comparing(SlideResponse::getOrder));
    organizationResponse.setSlides(slideResponse.getSlides());
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

  public static List<SlideResponse> convertTo(Collection<Slide> slides) {
    List<SlideResponse> slideResponses = new ArrayList<>();
    SlideResponse slideResponse;
    for (Slide slide : slides) {
      slideResponse = new SlideResponse();
      slideResponse.setImageUrl(slide.getImageUrl());
      slideResponse.setOrder(slide.getOrder());
      slideResponses.add(slideResponse);
    }
    return slideResponses;
  }

  public static RoleResponse convertTo(Role role) {
    RoleResponse roleResponse = new RoleResponse();
    roleResponse.setName(role.getName());
    roleResponse.setDescription(role.getDescription());
    return roleResponse;
  }

  public static List<RoleResponse> convertTo(List<Role> roles) {
    List<RoleResponse> roleResponses = new ArrayList<>();
    for (Role role : roles) {
      roleResponses.add(convertTo(role));
    }
    return roleResponses;
  }

  public static ActivityDetailsResponse convertTo(Activity activity) {
    ActivityDetailsResponse activityDetailsResponse = new ActivityDetailsResponse();
    activityDetailsResponse.setName(activity.getName());
    activityDetailsResponse.setContent(activity.getContent());
    activityDetailsResponse.setImage(activity.getImage());
    activityDetailsResponse.setTimestamp(activity.getTimestamps());
    return activityDetailsResponse;
  }

  public static ListUserResponse convertToListUserResponse(List<User> users) {
    List<UserResponse> userResponses = new ArrayList<>();
    for (User user : users) {
      UserResponse userResponse = new UserResponse();
      userResponse.setFirstName(user.getFirstName());
      userResponse.setLastName(user.getLastName());
      userResponse.setEmail(user.getEmail());
      userResponse.setPhoto(user.getPhoto());
      userResponse.setRoles(convertTo(user.getRoles()));
      userResponses.add(userResponse);
    }
    return new ListUserResponse(userResponses);
  }

  public static OrganizationResponse convertTo(Organization organization) {
    OrganizationResponse organizationResponse = new OrganizationResponse();
    BeanUtils.copyProperties(organization, organizationResponse);
    return organizationResponse;
  }

  public static SlideResponse convertToSlideDetailsResponse(Slide slide) {
    SlideResponse slideResponse = new SlideResponse();
    slideResponse.setOrganization(convertTo(slide.getOrganization()));
    BeanUtils.copyProperties(slide, slideResponse);
    return slideResponse;
  }

  public static ListCommentsResponse convertToListCommentsResponse(Collection<Comment> comments) {
    List<CommentResponse> commentResponses = new ArrayList<>();
    for (Comment comment : comments) {
      CommentResponse commentResponse = convertTo(comment);
      commentResponses.add(commentResponse);
    }
    return new ListCommentsResponse(commentResponses);
  }

  private static CommentResponse convertTo(Comment comment) {
    CommentResponse commentResponse = new CommentResponse();
    commentResponse.setUsername(comment.getUserId().getUsername());
    commentResponse.setBody(comment.getBody());
    commentResponse.setTimestamp(comment.getTimestamp());
    return commentResponse;
  }

  public static ListCategoryResponse convertToListCategoryResponse(
      Collection<Category> categories) {
    List<CategoryDetailsResponse> categoryResponses = new ArrayList<>();
    for (Category category : categories) {
      CategoryDetailsResponse categoryResponse = new CategoryDetailsResponse();
      categoryResponse.setName(category.getName());
      categoryResponses.add(categoryResponse);
    }
    return new ListCategoryResponse(categoryResponses);
  }

  public static UserResponse convertToAuthMe(User user) {
    UserResponse userResponse = new UserResponse();
    userResponse.setRoles(convertTo(user.getRoles()));
    userResponse.setFirstName(user.getFirstName());
    userResponse.setLastName(user.getLastName());
    userResponse.setEmail(user.getEmail());
    return userResponse;
  }
}
