package com.alkemy.ong.service;

import com.alkemy.ong.common.DtoUtils;
import com.alkemy.ong.common.EntityUtils;
import com.alkemy.ong.common.JwtUtils;
import com.alkemy.ong.config.security.ApplicationRole;
import com.alkemy.ong.exception.UserAlreadyExistException;
import com.alkemy.ong.model.entity.Role;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.UserAuthenticationRequest;
import com.alkemy.ong.model.request.UserDetailsRequest;
import com.alkemy.ong.model.response.ListUserResponse;
import com.alkemy.ong.model.response.UserAuthenticatedResponse;
import com.alkemy.ong.model.response.UserDetailsResponse;
import com.alkemy.ong.repository.IUserRepository;
import com.alkemy.ong.service.abstraction.IAuthenticationService;
import com.alkemy.ong.service.abstraction.IDeleteUserService;
import com.alkemy.ong.service.abstraction.IGetUserService;
import com.alkemy.ong.service.abstraction.IRegisterUserService;
import com.alkemy.ong.service.abstraction.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService, IDeleteUserService, IGetUserService,
    IRegisterUserService, IAuthenticationService {

  private static final String USER_NOT_FOUND_MESSAGE = "User not found.";
  private static final String USER_EMAIL_ERROR = "Email address is already used.";

  @Autowired
  private JwtUtils jwtUtils;
  @Autowired
  private IUserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private IRoleService roleService;
  @Autowired
  private AuthenticationManager authenticationManager;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return getUser(username);
  }

  @Override
  public ListUserResponse list() {
    List<User> users = userRepository.findAll();
    return EntityUtils.convertToListUserResponse(users);
  }

  @Override
  public User getBy(String authorizationHeader) {
    return getUser(jwtUtils.extractUsername(authorizationHeader));
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
    if (userRepository.findByEmail(userDetailsRequest.getEmail()) != null) {
      throw new UserAlreadyExistException(USER_EMAIL_ERROR);
    }

    User user = DtoUtils.convertTo(userDetailsRequest);
    user.setPassword(passwordEncoder.encode(userDetailsRequest.getPassword()));
    List<Role> roles = new ArrayList<>();
    roles.add(roleService.findBy(ApplicationRole.USER.getFullRoleName()));
    user.setRoles(roles);
    User userCreated = userRepository.save(user);
    UserDetailsResponse userDetailsResponse = EntityUtils.convertTo(userCreated);
    userDetailsResponse.setToken(jwtUtils.generateToken(userCreated));
    return userDetailsResponse;
  }

  @Override
  public UserAuthenticatedResponse authentication(
      UserAuthenticationRequest userAuthenticationRequest) {
    User user = getUser(userAuthenticationRequest.getEmail());

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(userAuthenticationRequest.getEmail(),
            userAuthenticationRequest.getPassword()));

    return new UserAuthenticatedResponse(jwtUtils.generateToken(user), user.getEmail());
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