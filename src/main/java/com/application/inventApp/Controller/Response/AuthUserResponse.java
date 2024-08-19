package com.application.inventApp.Controller.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@AllArgsConstructor
@Data
@Builder
public class AuthUserResponse {
  private String name;
  private String message;
  private boolean status;
  private String jwt;
}
