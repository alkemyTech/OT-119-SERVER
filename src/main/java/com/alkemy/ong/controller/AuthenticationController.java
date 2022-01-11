package com.alkemy.ong.controller;


import com.alkemy.ong.exception.UserAlreadyExistException;
import com.alkemy.ong.model.request.UserAuthenticationRequest;
import com.alkemy.ong.model.request.UserDetailsRequest;
import com.alkemy.ong.model.response.UserAuthenticatedResponse;
import com.alkemy.ong.model.response.UserDetailsResponse;
import com.alkemy.ong.service.abstraction.IAuthenticationService;
import com.alkemy.ong.service.abstraction.IRegisterUserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {


  @Autowired
  private IRegisterUserService registerUserService;
  @Autowired
  private IAuthenticationService authenticationService;

  @PostMapping("/login")
  public ResponseEntity<UserAuthenticatedResponse> login(
      @Valid @RequestBody UserAuthenticationRequest authenticationRequest) {
    return ResponseEntity.ok(authenticationService.authentication(authenticationRequest));
  }

  @PostMapping(value = "/register")
  public ResponseEntity<UserDetailsResponse> register(
      @Valid @RequestBody UserDetailsRequest userDetailsRequest)
      throws UserAlreadyExistException {
    UserDetailsResponse userDetailsResponse = registerUserService.register(userDetailsRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(userDetailsResponse);
  }

}
