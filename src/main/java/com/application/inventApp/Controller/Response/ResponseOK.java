package com.application.inventApp.Controller.Response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude
public class ResponseOK {

  private String message;

  public ResponseOK(String message){
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "ResponseOK{" +
        "message='" + message + '\'' +
        '}';
  }

}
