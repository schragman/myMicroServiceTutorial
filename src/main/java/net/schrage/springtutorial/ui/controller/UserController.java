package net.schrage.springtutorial.ui.controller;

import net.schrage.springtutorial.ui.model.request.UserDetailsRequestModel;
import net.schrage.springtutorial.ui.model.request.UserDetailsUpdateModel;
import net.schrage.springtutorial.ui.model.response.UserRest;
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

  Map<String, UserRest> users;

  @GetMapping(path = "/{userId}",
      produces = {
          MediaType.APPLICATION_XML_VALUE,
          MediaType.APPLICATION_JSON_VALUE
      })
  public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
    if (users.containsKey(userId)) {
      return new ResponseEntity<UserRest>(users.get(userId), HttpStatus.OK);
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
    UserRest returnValue = new UserRest();
    returnValue.setFirstName(userDetails.getFirstName());
    returnValue.setLastName(userDetails.getLastName());
    returnValue.setEmail(userDetails.getEmail());

    String userId = UUID.randomUUID().toString();
    returnValue.setUserId(userId);

    if (users == null) {
      users = new HashMap<>();
    }
    users.put(userId, returnValue);

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
    UserRest storedUser = users.get(userId);
    storedUser.setFirstName(userDetails.getFirstName());
    storedUser.setLastName(userDetails.getLastName());

    return new ResponseEntity<UserRest>(storedUser, HttpStatus.ACCEPTED);
  }

  @DeleteMapping(path = "/{userId}")
  public ResponseEntity<Void> deleteUser(@PathVariable String userId) {

    users.remove(userId);
    return ResponseEntity.noContent().build();
  }

}
