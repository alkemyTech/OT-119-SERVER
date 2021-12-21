package com.alkemy.ong.service;

import com.alkemy.ong.config.security.ApplicationRole;
import com.alkemy.ong.exception.OperationNotAllowedException;
import com.alkemy.ong.model.entity.Comment;
import com.alkemy.ong.model.entity.Role;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.repository.ICommentRepository;
import com.alkemy.ong.repository.INewsRepository;
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
  public void update(Long id, String authorizationHeader) throws OperationNotAllowedException {

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

  private void validateDataToComment(User user, Comment comment) {
    boolean isRoleAdmin = hasRole(ApplicationRole.ADMIN.getFullRoleName(), user.getRoles());
    boolean isRoleUser = hasRole(ApplicationRole.USER.getFullRoleName(), user.getRoles());
    boolean isTheSameId = comment.getUserId().getId().equals(user.getId());
    boolean isAdminOrUser = isRoleAdmin || isRoleUser;
    if (!isTheSameId && !isAdminOrUser) {
      String message = IS_NOT_ABLE_TO_ADD_COMMENT_MESSAGE;
      throw new OperationNotAllowedException(message);
    }
  }

  private void validateDataToSeeAllComments(User user) {
    boolean isRoleAdmin = hasRole(ApplicationRole.ADMIN.getFullRoleName(), user.getRoles());
    if (!isRoleAdmin) {
      String message = USER_IS_NOT_ABLE_TO_SEE_ALL_COMMENTS;
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
