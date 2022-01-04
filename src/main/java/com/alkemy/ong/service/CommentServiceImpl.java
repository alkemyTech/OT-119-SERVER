package com.alkemy.ong.service;

import com.alkemy.ong.common.EntityUtils;
import com.alkemy.ong.common.JwtUtils;
import com.alkemy.ong.config.security.ApplicationRole;
import com.alkemy.ong.exception.OperationNotAllowedException;
import com.alkemy.ong.model.entity.Comment;
import com.alkemy.ong.model.entity.Role;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.response.ListCommentsResponse;
import com.alkemy.ong.repository.ICommentRepository;
import com.alkemy.ong.service.abstraction.IDeleteCommentsService;
import com.alkemy.ong.service.abstraction.IGetCommentService;
import com.alkemy.ong.service.abstraction.IGetUserService;
import com.alkemy.ong.service.abstraction.IUpdateCommentService;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class CommentServiceImpl implements IDeleteCommentsService, IGetCommentService,
    IUpdateCommentService {
public class CommentServiceImpl implements IDeleteCommentsService, IGetCommentService {

  private static final String COMMENT_NOT_FOUND_MESSAGE = "Comment not found.";
  private static final String USER_IS_NOT_ABLE_TO_DELETE_COMMENT_MESSAGE = "User is not able to delete comment.";
  private static final String PERMISSION_DENIED_MESSAGE = "Permission denied";

  @Autowired
  private ICommentRepository commentRepository;

  @Autowired
  private IGetUserService getUserService;

  @Autowired
  private JwtUtils jwtUtils;

  @Override
  public void delete(Long id, String authorizationHeader) throws OperationNotAllowedException {
    Comment comment = getComment(id);

    throwExceptionIfOperationIsNotAllowed(
        getUserService.getBy(authorizationHeader),
        comment,
        USER_IS_NOT_ABLE_TO_DELETE_COMMENT_MESSAGE);

    commentRepository.delete(comment);
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

  private Comment getComment(Long id) {
    Optional<Comment> commentOptional = commentRepository.findById(id);
    if (commentOptional.isEmpty()) {
      throw new EntityNotFoundException(COMMENT_NOT_FOUND_MESSAGE);
    }
    return commentOptional.get();
  }

  @Override
  public ListCommentsResponse getComments(Long newsId) {
    List<Comment> comments = commentRepository.findAllCommentsByNewsId(newsId);

    ListCommentsResponse commentResponse = new ListCommentsResponse();
    commentResponse.setComments((EntityUtils.convertToListCommentsResponse(comments)));
    return commentResponse;
  }

  @Override
  public void updateComment(String body, long id, String authorizationHeader) {
    User user = getUserService.getBy(authorizationHeader);
    String role = user.getRoles().get(0).getName();
    Optional<Comment> comment = commentRepository.findById(id);
    if (comment.isEmpty()) {
      throw new EntityNotFoundException(COMMENT_NOT_FOUND_MESSAGE);
    }
    String commentAuthor = comment.get().getUserId().getEmail();
    if (user.getEmail().equals(commentAuthor) || role.equals("ROLE_ADMIN")) {
      commentRepository.updateComment(body, id);
    } else {
      throw new OperationNotAllowedException(PERMISSION_DENIED_MESSAGE);
    }
  }
}
