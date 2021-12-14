package com.alkemy.ong.exception;

import com.alkemy.ong.model.response.ErrorResponse;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

  @org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleEntityNotFoundException(
      HttpServletRequest request,
      EntityNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(buildResponse(e, HttpStatus.NOT_FOUND));
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ErrorResponse> handleAuthenticationException(
      HttpServletRequest request,
      AuthenticationException e) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(buildResponse(e, HttpStatus.UNAUTHORIZED));
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(InvalidCredentialsException.class)
  public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(
      HttpServletRequest request,
      InvalidCredentialsException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(buildResponse(e, HttpStatus.BAD_REQUEST));
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(
      HttpServletRequest request,
      UsernameNotFoundException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(buildResponse(e, HttpStatus.BAD_REQUEST));
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ErrorResponse> handleBadCredentialsException(
      HttpServletRequest request,
      BadCredentialsException e) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(buildResponse(e, HttpStatus.UNAUTHORIZED));
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(
      HttpServletRequest request,
      Exception e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(buildResponse(e, HttpStatus.INTERNAL_SERVER_ERROR));
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(OperationNotAllowedException.class)
  public ResponseEntity<ErrorResponse> handleOperationNotAllowedException(
      HttpServletRequest request,
      OperationNotAllowedException e) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN)
        .body(buildResponse(e, HttpStatus.FORBIDDEN));
  }

  private ErrorResponse buildResponse(Exception e, HttpStatus httpStatus) {
    return new ErrorResponse(e, httpStatus.value());
  }

  private ErrorResponse buildResponse(String message, HttpStatus httpStatus) {
    return new ErrorResponse(message, httpStatus.value());
  }

}
