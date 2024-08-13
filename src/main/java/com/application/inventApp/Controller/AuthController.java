package com.application.inventApp.Controller;

import com.application.inventApp.Controller.Response.ResponseOK;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
  @GetMapping("/login")
  public ResponseEntity<?> login(){
    return new ResponseEntity<>(new ResponseOK("Hola te saluda el endpoint de login"), HttpStatus.OK);
  }
}
