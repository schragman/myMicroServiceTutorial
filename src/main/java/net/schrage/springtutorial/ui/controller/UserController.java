package net.schrage.springtutorial.ui.controller;

import net.schrage.springtutorial.ui.model.request.UserDetailsRequestModel;
import net.schrage.springtutorial.ui.model.response.UserRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;

@RestController
@RequestMapping("users")  //http://localhost:8080/users
public class UserController {

  @GetMapping(path = "/{userId}",
      produces = {
          MediaType.APPLICATION_XML_VALUE,
          MediaType.APPLICATION_JSON_VALUE
      })
  public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
    UserRest returnValue = new UserRest();
    returnValue.setFirstName("Michael");
    returnValue.setLastName("Schrage");
    returnValue.setEmail("test@schrage.net");

    return new ResponseEntity<UserRest>(returnValue, HttpStatus.ACCEPTED);
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

    return new ResponseEntity<UserRest>(returnValue, HttpStatus.ACCEPTED);
  }

  @PutMapping
  public String updateUser() {
    return "User was updated";
  }

  @DeleteMapping
  public String deleteUser() {
    return "User was deleted";
  }

}
