package com.alkemy.ong.service;

import com.alkemy.ong.common.DtoUtils;
import com.alkemy.ong.common.JwtUtil;
import com.alkemy.ong.common.EntityUtils;
import com.alkemy.ong.exception.UserAlreadyExistException;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.UserDetailsRequest;
import com.alkemy.ong.model.response.UserDetailsResponse;
import com.alkemy.ong.repository.IUserRepository;
import com.alkemy.ong.service.abstraction.IDeleteUserService;
import com.alkemy.ong.service.abstraction.IGetUserService;
import com.alkemy.ong.service.abstraction.RegisterUserService;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService, IDeleteUserService, IGetUserService,
    RegisterUserService {

  private static final String USER_NOT_FOUND_MESSAGE = "User not found.";
  private static final String USER_EMAIL_ERROR = "email address is already used.";

  @Autowired
  private JwtUtil jwtUtil;
  @Autowired
  private IUserRepository userRepository;
  @Autowired
  private DtoUtils dtoUtils;
  @Autowired
  private EntityUtils entityUtils;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return getUser(username);
  }

  @Override
  public User getBy(String authorizationHeader) {
    return getUser(jwtUtil.extractUsername(authorizationHeader));
  }

  @Override
  public void delete(Long id) throws EntityNotFoundException {
    User user = getUser(id);
    user.setSoftDeleted(true);
    userRepository.save(user);
  }

  @Override
  public UserDetailsResponse register(UserDetailsRequest userDetailsRequest)
      throws UserAlreadyExistException {
    User userEntity = dtoUtils.convertTo(userDetailsRequest);
    if (userRepository.findByEmail(userEntity.getEmail()) != null) {
      throw new UserAlreadyExistException(USER_EMAIL_ERROR);
    }
    User userSaved = userRepository.save(userEntity);
    return entityUtils.convertTo(userSaved);
  }

  private User getUser(Long id) {
    Optional<User> userOptional = userRepository.findById(id);
    if (userOptional.isEmpty() || userOptional.get().isSoftDeleted()) {
      throw new EntityNotFoundException(USER_NOT_FOUND_MESSAGE);
    }
    return userOptional.get();
  }

  private User getUser(String username) {
    User user = userRepository.findByEmail(username);
    if (user == null) {
      throw new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE);
    }
    return user;
  }

}
