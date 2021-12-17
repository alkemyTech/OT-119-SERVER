package com.alkemy.ong.service;

import com.alkemy.ong.common.DtoUtils;
import com.alkemy.ong.common.JwtUtil;
import com.alkemy.ong.common.EntityUtils;
import com.alkemy.ong.exception.InvalidCredentialsException;
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
  private static final String USER_EMAIL_ERROR = "This email address is already used.";

  @Autowired
  private JwtUtil jwtUtil;
  @Autowired
  private IUserRepository userRepository;
  @Autowired
  private EntityUtils entityUtils;
  @Autowired
  private DtoUtils dtoUtils;


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
      throws InvalidCredentialsException {
    User userEntity = dtoUtils.userDTO2Entity(userDetailsRequest);
    if (userRepository.findByEmail(userEntity.getEmail()) != null) {
      throw new InvalidCredentialsException(USER_EMAIL_ERROR);
    }
    User userSaved = userRepository.save(userEntity);
    return entityUtils.userEntity2DTO(userSaved);
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
