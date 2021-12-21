package com.alkemy.ong.service;

import com.alkemy.ong.config.security.ApplicationRole;
import com.alkemy.ong.exception.CommentNotFoundException;
import com.alkemy.ong.exception.OperationNotAllowedException;
import com.alkemy.ong.model.entity.Comment;
import com.alkemy.ong.model.entity.Role;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.repository.ICommentRepository;
import com.alkemy.ong.service.abstraction.*;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements IDeleteCommentsService,
                                           IPostCommentsService,
                                           IGetCommentsService,
                                           IPutCommentsService {

  private static final String COMMENT_NOT_FOUND_MESSAGE = "Comment not found.";
  private static final String USER_IS_NOT_ABLE_TO_DELETE_COMMENT_MESSAGE = "User is not able to delete comment.";
  private static final String IS_NOT_ABLE_TO_ADD_COMMENT_MESSAGE = "Is not able to add comment.";
  private static final String USER_IS_NOT_ABLE_TO_SEE_ALL_COMMENTS = "User is not able to see all comments.";

  @Autowired
  private ICommentRepository commentRepository;

  @Autowired
  private IGetUserService getUserService;

  @Override
  public void delete(Long id, String authorizationHeader) throws OperationNotAllowedException {
    Comment comment = getComment(id);

    throwExceptionIfOperationIsNotAllowed(
        getUserService.getBy(authorizationHeader),
        comment,
        USER_IS_NOT_ABLE_TO_DELETE_COMMENT_MESSAGE);

    commentRepository.delete(comment);
  }

  @Override
  public void add(Comment comment, String authorizationHeader) throws OperationNotAllowedException {
    User user = getUserService.getBy(authorizationHeader);
    validateDataToComment(user, comment);
    commentRepository.save(comment);
  }

  @Override
  public List<Comment> getComments(String authorizationHeader) throws OperationNotAllowedException {
    User user = getUserService.getBy(authorizationHeader);
    validateDataToSeeAllComments(user);
    List<Comment> comments = commentRepository.findByOrderByTimestampAsc();
    return comments;
  }

  @Override
  public void update(Long id, Comment comment, String authorizationHeader) throws OperationNotAllowedException {
    User user = getUserService.getBy(authorizationHeader);
    validateDataToUpdateComment(user,comment);
    Comment commentToUpdate = commentRepository.getById(comment.getId());
    String bodyUpdated = comment.getBody();
    commentToUpdate.setBody(bodyUpdated);
  }

  private boolean hasRole(String nameRole, List<Role> roles) {
    return roles.stream().anyMatch(role -> nameRole.equals(role.getName()));
  }

  private void throwExceptionIfOperationIsNotAllowed(User user, Comment comment, String message) {
    boolean isRoleAdmin = hasRole(ApplicationRole.ADMIN.getFullRoleName(), user.getRoles());
    if (!comment.getUserId().getId().equals(user.getId()) && !isRoleAdmin) {
      throw new OperationNotAllowedException(message);
    }
  }

  private boolean isAdmin(User user){
    boolean isRoleAdmin = hasRole(ApplicationRole.ADMIN.getFullRoleName(), user.getRoles());
    return isRoleAdmin;
  }

  private boolean isUser(User user){
    boolean isRoleUser = hasRole(ApplicationRole.USER.getFullRoleName(), user.getRoles());
    return isRoleUser;
  }

  private boolean isAdminOrUser(User user){
    boolean isAdminOrUser = this.isAdmin(user) || this.isUser(user);
    return isAdminOrUser;
  }
  private void validateDataToComment(User user, Comment comment) {
    boolean isTheSameId = comment.getUserId().getId().equals(user.getId());
    if (!isTheSameId && !isAdminOrUser(user)) {
      String message = IS_NOT_ABLE_TO_ADD_COMMENT_MESSAGE;
      throw new OperationNotAllowedException(message);
    }
  }

  private void validateDataToSeeAllComments(User user) {
    if (!isAdmin(user)) {
      String message = USER_IS_NOT_ABLE_TO_SEE_ALL_COMMENTS;
      throw new OperationNotAllowedException(message);
    }
  }

  private boolean areFromTheSameUser(User user,Comment comment){
    boolean isTheSameUser = false;
    Long comment_id = comment.getId();
    Comment commentToUpdate = commentRepository.getById(comment_id);
    if (commentToUpdate != null){
      isTheSameUser = commentToUpdate.getUserId().equals(user);
    } else {
      String message = "COMMENT NOT EXIST IN DATABASE";
      throw new CommentNotFoundException(message);
    }
    return isTheSameUser;
  }

  private void validateDataToUpdateComment(User user,Comment comment) throws OperationNotAllowedException {
    if (!areFromTheSameUser(user,comment) || !isAdmin(user)){
      String message = "ARE NOT THE AUTOR OF COMMENT OR ADMIN";
      throw new OperationNotAllowedException(message);
    }
  }

  private Comment getComment(Long id) {
    Optional<Comment> commentOptional = commentRepository.findById(id);
    if (commentOptional.isEmpty()) {
      throw new EntityNotFoundException(COMMENT_NOT_FOUND_MESSAGE);
    }
    return commentOptional.get();
  }
}
