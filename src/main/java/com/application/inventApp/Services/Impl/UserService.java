package com.application.inventApp.Services.Impl;

import com.application.inventApp.Controller.DTO.AuthUserRequest;
import com.application.inventApp.Controller.Response.AuthUserResponse;
import com.application.inventApp.Entity.User;
import com.application.inventApp.Repository.UserRepository;
import com.application.inventApp.Services.IUserService;
import com.application.inventApp.Utils.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements IUserService, UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtUtils jwtUtils;

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
    try {
      Optional<User> userOptional = userRepository.findUserByname(username);
      User user = userOptional
          .orElseThrow(() -> new UsernameNotFoundException("El usuario: " + username + "no existe"));
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

  /* sdddd */
  public AuthUserResponse loginUser(AuthUserRequest user) {
    String username = user.getName();
    String password = user.getPassword();

    Authentication authentication = this.authenticate(username, password);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    Collection<?> roles = authentication.getAuthorities().stream()
        .map(grantedAuthority -> grantedAuthority.getAuthority()).toList();
    String accesToken = jwtUtils.createToken(authentication);

    AuthUserResponse authUserResponse = new AuthUserResponse(username, "Ususario authenticado correctamente", true,
        roles, accesToken);
    return authUserResponse;

  }

  public Authentication authenticate(String username, String password) {
    UserDetails userDetails = this.loadUserByUsername(username);
    if (userDetails == null)
      throw new BadCredentialsException("Nombre de usuario o contraseña incorrectos.");

    if (!passwordEncoder.matches(password, userDetails.getPassword()))
      throw new BadCredentialsException("Contraseña incorrecta.");

    return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());

  }

}
