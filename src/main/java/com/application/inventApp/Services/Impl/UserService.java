package com.application.inventApp.Services.Impl;

import com.application.inventApp.Controller.DTO.AuthUserRequest;
import com.application.inventApp.Controller.Response.AuthUserResponse;
import com.application.inventApp.Entity.User;
import com.application.inventApp.Repository.UserRepository;
import com.application.inventApp.Services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements IUserService, UserDetailsService {

  @Autowired
  private UserRepository userRepository;




  @Override
  public List<User> findAll() {
    return (List<User>) userRepository.findAll();
  }


  @Override
  public Optional<User> findById(UUID id) {
    return userRepository.findById(id);
  }

  @Override
  public void save(User user) {
    BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
    user.setPassword(bcrypt.encode(user.getPassword()));
    userRepository.save(user);
  }

  @Override
  public Optional<User> update(UUID id, User user) {
    Optional<User> userOptional = userRepository.findById(id);


    if (userOptional.isPresent()) {

      User userUp = userOptional.get();
      userUp.setName(user.getName());
      userUp.setPassword(user.getPassword());
      userUp.setRol(user.getRol());

      userRepository.save(userUp);
      return userOptional;
    }
    return userOptional;
  }

  @Override
  public Optional<User> deleate(UUID id) {
    Optional<User> userOptional = userRepository.findById(id);
    if (userOptional.isPresent()) {
      userRepository.delete(userOptional.get());
      return userOptional;
    }
    return userOptional;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {
      Optional<User> userOptional = userRepository.findUserByname(username);
      User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("El usuario: " + username + "no existe"));
      System.out.println(user);
      List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
      authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(user.getRol().name())));

      return new org.springframework.security.core.userdetails.User(
          user.getName(),
          user.getPassword(),
          user.isEnabled(),
          user.isAccountNoExpired(),
          user.isCredentialNoExpired(),
          user.isAccountNoLocked(),
          authorityList);


    } catch (Exception e) {
      System.out.println(e + " <----Login exceptions---------");
      return null;
    }
  }
}
