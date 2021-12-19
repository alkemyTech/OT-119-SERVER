package com.alkemy.ong.controller;

import com.alkemy.ong.common.JwtUtil;
import com.alkemy.ong.model.request.UserAuthenticationRequest;
import com.alkemy.ong.model.response.UserAuthenticatedResponse;
import com.alkemy.ong.repository.IRoleRepository;
import com.alkemy.ong.repository.IUserRepository;
import com.alkemy.ong.service.abstraction.IGetUserService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  @Autowired
  private JwtUtil jwtUtil;
  @Autowired
  private IGetUserService getUserService;

  @PostMapping("/login")
  public ResponseEntity<?> login(@Valid
  @RequestBody UserAuthenticationRequest authRequest) throws Exception {
    Authentication auth = getUserService.authentication(authRequest);
    UserDetails user = (UserDetails) auth.getPrincipal();
    String jwt = jwtUtil.generateToken(user);
    return ResponseEntity.ok(new UserAuthenticatedResponse(jwt, user.getUsername()));
  }

}
