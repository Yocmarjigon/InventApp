package com.application.inventApp.Exception;

import com.application.inventApp.Enums.Severity;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControlllerAdvice {

  @ExceptionHandler( StockException.class)
  public ResponseEntity<?> stockException( StockException e){
    return new ResponseEntity<>(new ExceptionDetails(e.getMessage(), Severity.ERROR), HttpStatus.BAD_REQUEST);
  }
  @ExceptionHandler(JWTVerificationException.class)
  public  ResponseEntity<?> verificationException(JWTVerificationException e){
    return new ResponseEntity<>(new ExceptionDetails(e.getMessage(), Severity.ERROR), HttpStatus.BAD_REQUEST);
  }
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionDetails> globalExceptions(Exception e) {
    System.out.println(e);
    return new ResponseEntity<>(new ExceptionDetails("Internal Server Error", Severity.ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
