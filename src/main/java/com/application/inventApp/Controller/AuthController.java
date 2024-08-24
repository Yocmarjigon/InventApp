package com.application.inventApp.Controller;

import com.application.inventApp.Controller.DTO.AuthUserRequest;
import com.application.inventApp.Controller.Response.ResponseOK;
import com.application.inventApp.Services.Impl.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  private UserService userService;

  @CrossOrigin
  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody @Valid AuthUserRequest user, BindingResult bindingResult) throws BadCredentialsException {
    if (bindingResult.hasErrors()) {
      return new ResponseEntity<>(new ResponseOK(bindingResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList()), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(userService.loginUser(user), HttpStatus.OK);
  }
}
