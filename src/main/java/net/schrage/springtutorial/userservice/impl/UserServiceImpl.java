package net.schrage.springtutorial.userservice.impl;

import net.schrage.springtutorial.shared.Utils;
import net.schrage.springtutorial.ui.model.request.UserDetailsRequestModel;
import net.schrage.springtutorial.ui.model.response.UserRest;
import net.schrage.springtutorial.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

  private Map<String, UserRest> users;
  private Utils utils;

  public UserServiceImpl() {      //Das Spring-Framework braucht einen parameterlosen Konstruktor
  }

  @Autowired
  public UserServiceImpl(Utils utils) {
    this.utils = utils;
  }


  @Override
  public UserRest createUser(UserDetailsRequestModel userDetails) {
    UserRest returnValue = new UserRest();
    returnValue.setFirstName(userDetails.getFirstName());
    returnValue.setLastName(userDetails.getLastName());
    returnValue.setEmail(userDetails.getEmail());

    String userId = utils.generateUserId();
    returnValue.setUserId(userId);

    if (users == null) {
      users = new HashMap<>();
    }
    users.put(userId, returnValue);

    return returnValue;
  }

  @Override
  public Map<String, UserRest> getUsers() {
    return this.users;
  }

}
