package com.application.inventApp.Exception;

import com.application.inventApp.Enums.Severity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControlllerAdvice {
  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<?> usernameNotFoundException(UserNotFountException e){
    return new ResponseEntity<>( new ExceptionDetails("EL usuario ", Severity.ERROR), HttpStatus.NOT_FOUND);

  }
}
