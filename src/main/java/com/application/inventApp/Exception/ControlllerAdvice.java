package com.application.inventApp.Exception;

import com.application.inventApp.Enums.Severity;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControlllerAdvice {
  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<ExceptionDetails> usernameNotFoundException(UserNotFountException e){
    return new ResponseEntity<>( new ExceptionDetails("EL usuario ", Severity.ERROR), HttpStatus.NOT_FOUND);

  }
  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ExceptionDetails> badCredentialsException(BadCredentialsException e){
    return new ResponseEntity<>(new ExceptionDetails("El usuario o contraseña son incorrectos", Severity.ERROR), HttpStatus.BAD_REQUEST);
  }
  @ExceptionHandler(JWTVerificationException.class)
  public ResponseEntity<ExceptionDetails> tokenInvalid(JWTVerificationException e){
    return new ResponseEntity<>(new ExceptionDetails("Token invalido o expirado", Severity.ERROR), HttpStatus.BAD_REQUEST);
  }
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionDetails> globalExceptions(Exception e){
    return new ResponseEntity<>(new ExceptionDetails("Internal Server Error", Severity.ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
