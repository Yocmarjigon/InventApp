package com.application.inventApp.Controller;

import com.application.inventApp.Controller.DTO.AuthUserRequest;
import com.application.inventApp.Controller.Response.AuthUserResponse;
import com.application.inventApp.Controller.Response.ResponseOK;
import com.application.inventApp.Services.Impl.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
@Autowired
private UserService userService;

  @PostMapping("/login")
  public ResponseEntity<AuthUserResponse> login(@RequestBody @Valid AuthUserRequest user){
    return new ResponseEntity<>(userService.loginUser(user), HttpStatus.OK);
  }
}
