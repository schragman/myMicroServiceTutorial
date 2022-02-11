package net.schrage.springtutorial.exceptions;

public class UserServiceException extends RuntimeException {

  private final long serialVersionUID = -2207764014921730340L;

  public UserServiceException(String message) {
    super(message);
  }
}
