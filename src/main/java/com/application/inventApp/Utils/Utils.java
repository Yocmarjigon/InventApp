package com.application.inventApp.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.application.inventApp.Entity.User;
import com.application.inventApp.Exception.NotFoundException;
import com.application.inventApp.Repository.UserRepository;

@Component
public class Utils {

  @Autowired
  UserRepository userRepository;
  
  public User findUserLogging() throws NotFoundException{
     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      User user = this.userRepository.findUserByname(authentication.getPrincipal().toString()).orElseThrow(() -> new NotFoundException("El ususario no existe"));
      return user;
  }
}
