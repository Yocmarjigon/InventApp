package com.application.inventApp.Exception;


public class NotFoundException extends Exception {
  private ExceptionDetails exceptionDetails;

  public NotFoundException(Exception e, ExceptionDetails exceptionDetails) {
    super(e);
    this.exceptionDetails = exceptionDetails;
  }

  public NotFoundException(String message) {
    super(message);
  }
}
