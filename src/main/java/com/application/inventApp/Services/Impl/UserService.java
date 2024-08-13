package com.application.inventApp.Services.Impl;

import com.application.inventApp.Entity.User;
import com.application.inventApp.Repository.UserRepository;
import com.application.inventApp.Services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService , UserDetailsService {

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
    Optional<User> userOptional = userRepository.findUserByname(username);
    if (userOptional.isPresent()){
      User user = userOptional.get();
      List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
      authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(user.getRol().name())));
      authorityList.add(new SimpleGrantedAuthority(user.getName()));

      return new org.springframework.security.core.userdetails.User(
          user.getName(),
          user.getPassword(),
          user.isEnabled(),
          user.isAccountNoExpired(),
          user.isCredentialNoExpired(),
          user.isAccountNoLocked(),
          authorityList);
    }
    return null;
  }
}
