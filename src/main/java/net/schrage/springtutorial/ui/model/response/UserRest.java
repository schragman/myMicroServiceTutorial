package net.schrage.springtutorial.ui.model.response;

public class UserRest {
  private String firstName;
  private String lastName;
  private String email;
  private String UserId;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUserId() {
    return UserId;
  }

  public void setUserId(String userId) {
    UserId = userId;
  }
}
