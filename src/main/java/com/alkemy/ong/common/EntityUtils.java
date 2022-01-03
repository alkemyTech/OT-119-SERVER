package com.alkemy.ong.common;

import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.model.entity.Comment;
import com.alkemy.ong.model.entity.News;
import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.entity.Role;
import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.response.CategoryDetailsResponse;
import com.alkemy.ong.model.response.CommentResponse;
import com.alkemy.ong.model.response.ListUserResponse;
import com.alkemy.ong.model.response.NewsDetailsResponse;
import com.alkemy.ong.model.response.OrganizationResponse;
import com.alkemy.ong.model.response.RoleResponse;
import com.alkemy.ong.model.response.SlideResponse;
import com.alkemy.ong.model.response.UserDetailsResponse;
import com.alkemy.ong.model.response.UserResponse;
import java.util.ArrayList;
import java.util.Collection;
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

  public static List<CommentResponse> convertToListCommentsResponse(Collection<Comment> comments) {
    List<CommentResponse> commentResponses = new ArrayList<>();
    for (Comment comment : comments) {
      CommentResponse commentResponse = new CommentResponse();
      commentResponse.setUsername(comment.getUserId().getUsername());
      commentResponse.setBody(comment.getBody());
      commentResponse.setTimestamp(comment.getTimestamp());
      commentResponses.add(commentResponse);
    }
    return commentResponses;
  }
}
