package com.alkemy.ong.controller;

<<<<<<< Updated upstream
=======
import com.alkemy.ong.model.response.ListUserResponse;
>>>>>>> Stashed changes
import com.alkemy.ong.model.response.UserResponse;
import com.alkemy.ong.service.abstraction.IDeleteUserService;
import com.alkemy.ong.service.abstraction.IGetUserService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  public IDeleteUserService deleteUserService;
  @Autowired
  public IGetUserService getUserService;


  @Autowired
  public IGetUserService getUserService;

  @DeleteMapping(value = "/users/{id}")
  public ResponseEntity<Empty> delete(@PathVariable Long id) throws EntityNotFoundException {
    deleteUserService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping(value = "/users")
<<<<<<< Updated upstream
  public ResponseEntity<List<UserResponse>> get() {
    List<UserResponse> userResponseList = getUserService.getListUserResponse();
    return new ResponseEntity<>(userResponseList, HttpStatus.OK);
=======
  public ResponseEntity<List<UserResponse>> getList() {
    ListUserResponse userResponses = getUserService.getList();
    return new ResponseEntity<>(userResponses, HttpStatus.OK);
>>>>>>> Stashed changes
  }
}
