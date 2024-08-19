package com.application.inventApp.Services.Impl;

import com.application.inventApp.Entity.User;
import com.application.inventApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserDetailImplsService implements org.springframework.security.core.userdetails.UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findUserByname(username).orElseThrow(() -> new UsernameNotFoundException("El usuario: " + username + "no existe"));

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
}
