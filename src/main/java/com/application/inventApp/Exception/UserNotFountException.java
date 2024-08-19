package com.application.inventApp.Exception;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserNotFountException extends Exception{
  ExceptionDetails exceptionDetails;

  public UserNotFountException(Exception e, ExceptionDetails exceptionDetails){
      super(e);
      this.exceptionDetails = exceptionDetails;
    }


}
