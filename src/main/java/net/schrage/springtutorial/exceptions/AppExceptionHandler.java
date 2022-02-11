package net.schrage.springtutorial.exceptions;

import net.schrage.springtutorial.ui.model.response.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice  // Makes this class listen for Exceptions
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {Exception.class})  //Behandelt alle Exceptions außer die specifischeren
  public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {

    String contextPath = request.getRemoteUser();
    String errorMessageDescription = ex.getLocalizedMessage() == null ? ex.toString() : ex.getLocalizedMessage();
    ErrorMessage errorMessage = new ErrorMessage(contextPath + "\n" + errorMessageDescription, new Date());


    return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = {NullPointerException.class, UserServiceException.class})  //Behandelt Exceptions
  public ResponseEntity<Object> handleSpecificExceptions(Exception ex, WebRequest request) {

    String errorMessageDescription = ex.getLocalizedMessage() == null ? ex.toString() : ex.getLocalizedMessage();
    ErrorMessage errorMessage = new ErrorMessage(request.getRemoteUser() + "\n" + errorMessageDescription, new Date());

    return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
