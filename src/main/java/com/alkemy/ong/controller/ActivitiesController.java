package com.alkemy.ong.controller;

import com.alkemy.ong.exception.OperationNotAllowedException;
import com.alkemy.ong.service.abstraction.IDeleteCommentsService;
import com.alkemy.ong.service.abstraction.IPostActivityService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ActivitiesController {

    @Autowired
    private IPostActivityService iPostActivityService;

    @PostMapping(value = "/activities")
    public ResponseEntity<Object> addActivity(@RequestHeader(value = "Authorization") String authorizationHeader,
                                              @RequestBody Map data) {
        String name = (String) data.get("name");
        String content = (String) data.get("content");
        try {
            iPostActivityService.add(name,content,authorizationHeader);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (OperationNotAllowedException exception){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
