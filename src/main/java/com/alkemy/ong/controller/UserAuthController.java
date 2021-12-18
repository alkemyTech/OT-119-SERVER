package com.alkemy.ong.controller;

import com.alkemy.ong.common.JwtUtil;
import com.alkemy.ong.model.request.AuthenticationRequest;
import com.alkemy.ong.model.response.JwtResponse;
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

public class UserAuthController {

  @Autowired
  private UserDetailsService userDetailsService;
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private JwtUtil jwtUtil;
  @Autowired
  private IGetUserService userService;
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;
  @Autowired
  private IUserRepository userRepository;
  @Autowired
  private IRoleRepository roleRepository;

  @PostMapping("/login")
  public ResponseEntity<?> login(@Valid
  @RequestBody AuthenticationRequest authRequest) throws Exception {

    Authentication auth = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
    );
    SecurityContextHolder.getContext().setAuthentication(auth);
    String jwt = jwtUtil.generateJwtToken(auth);

    UserDetails userDetails = (UserDetails) auth.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
        .collect(
            Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), roles));
  }

}
