package net.schrage.springtutorial.userservice;

import net.schrage.springtutorial.ui.model.request.UserDetailsRequestModel;
import net.schrage.springtutorial.ui.model.response.UserRest;

import java.util.Map;

public interface UserService {
  UserRest createUser(UserDetailsRequestModel comingUser);

  Map<String, UserRest> getUsers();

}
