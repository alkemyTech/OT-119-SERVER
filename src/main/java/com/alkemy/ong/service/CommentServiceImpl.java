package com.alkemy.ong.service;

import com.alkemy.ong.config.security.ApplicationRole;
import com.alkemy.ong.exception.OperationNotAllowedException;
import com.alkemy.ong.model.entity.Comment;
import com.alkemy.ong.model.entity.Role;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.repository.ICommentRepository;
import com.alkemy.ong.service.abstraction.IDeleteCommentsService;
import com.alkemy.ong.service.abstraction.IGetUserService;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements IDeleteCommentsService {

  private static final String COMMENT_NOT_FOUND_MESSAGE = "Comment not found.";
  private static final String USER_IS_NOT_ABLE_TO_DELETE_COMMENT_MESSAGE = "User is not able to delete comment.";

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

}
