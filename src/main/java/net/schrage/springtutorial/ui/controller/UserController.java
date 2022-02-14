package net.schrage.springtutorial.ui.controller;

import net.schrage.springtutorial.exceptions.UserServiceException;
import net.schrage.springtutorial.ui.model.request.UserDetailsRequestModel;
import net.schrage.springtutorial.ui.model.request.UserDetailsUpdateModel;
import net.schrage.springtutorial.ui.model.response.UserRest;
import net.schrage.springtutorial.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("users")  //http://localhost:8080/users
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping(path = "/{userId}",
      produces = {
          MediaType.APPLICATION_XML_VALUE,
          MediaType.APPLICATION_JSON_VALUE
      })
  public ResponseEntity<UserRest> getUser(@PathVariable String userId) {

    /*if (true) {
      throw new UserServiceException("A user service exception is thrown");
    }*/


    if (userService.getUsers().containsKey(userId)) {
      return new ResponseEntity<UserRest>(userService.getUsers().get(userId), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

  }

  @GetMapping
  public String getUsers(@RequestParam(value = "page") int page,
                         @RequestParam(value = "limit", defaultValue = "40") int limit) {
    return "Get User of page " + page + " and limit of " + limit;
  }

  @PostMapping(consumes = {
      MediaType.APPLICATION_XML_VALUE,
      MediaType.APPLICATION_JSON_VALUE},
      produces = {
          MediaType.APPLICATION_XML_VALUE,
          MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {

    UserRest returnValue = userService.createUser(userDetails);

    return new ResponseEntity<UserRest>(returnValue, HttpStatus.ACCEPTED);
  }

  @PutMapping(path = "/{userId}", consumes = {
      MediaType.APPLICATION_XML_VALUE,
      MediaType.APPLICATION_JSON_VALUE},
      produces = {
          MediaType.APPLICATION_XML_VALUE,
          MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<UserRest> updateUser(
      @PathVariable String userId, @Valid @RequestBody UserDetailsUpdateModel userDetails) {
    UserRest storedUser = userService.getUsers().get(userId);
    storedUser.setFirstName(userDetails.getFirstName());
    storedUser.setLastName(userDetails.getLastName());

    return new ResponseEntity<UserRest>(storedUser, HttpStatus.ACCEPTED);
  }

  @DeleteMapping(path = "/{userId}")
  public ResponseEntity<Void> deleteUser(@PathVariable String userId) {

    userService.getUsers().remove(userId);
    return ResponseEntity.noContent().build();
  }

}
