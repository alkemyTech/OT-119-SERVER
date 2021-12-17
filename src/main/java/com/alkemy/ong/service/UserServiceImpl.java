package com.alkemy.ong.service;

import com.alkemy.ong.common.JwtUtil;
import com.alkemy.ong.common.UserModelUtils;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.UserRequest;
import com.alkemy.ong.model.response.UserResponse;
import com.alkemy.ong.repository.IUserRepository;
import com.alkemy.ong.service.abstraction.IDeleteUserService;
import com.alkemy.ong.service.abstraction.IGetUserService;
import com.alkemy.ong.service.abstraction.IPostUserService;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService, IDeleteUserService, IGetUserService,
    IPostUserService {

  private static final String USER_NOT_FOUND_MESSAGE = "User not found.";

  @Autowired
  private JwtUtil jwtUtil;
  @Autowired
  private IUserRepository userRepository;
  @Autowired
  private UserModelUtils userModelUtils;


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
    com.alkemy.ong.model.entity.User user = getUser(id);
    user.setSoftDeleted(true);
    userRepository.save(user);
  }

  @Override
  public UserResponse save(UserRequest dto) {
    User userEntity = userModelUtils.userDTO2Entity(dto);
    User userSaved = userRepository.save(userEntity);
    UserResponse result = userModelUtils.userEntity2DTO(userSaved);
    return result;
  }

  private User getUser(Long id) {
    Optional<com.alkemy.ong.model.entity.User> userOptional = userRepository.findById(id);
    if (userOptional.isEmpty() || userOptional.get().isSoftDeleted()) {
      throw new EntityNotFoundException(USER_NOT_FOUND_MESSAGE);
    }
    return userOptional.get();
  }

  private User getUser(String username) {
    com.alkemy.ong.model.entity.User user = userRepository.findByEmail(username);
    if (user == null) {
      throw new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE);
    }
    return user;
  }

}
