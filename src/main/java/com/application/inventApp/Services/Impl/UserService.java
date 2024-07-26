package com.application.inventApp.Services.Impl;

import com.application.inventApp.Entity.User;
import com.application.inventApp.Repository.UserRepository;
import com.application.inventApp.Services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService {

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
    if (userOptional.isPresent()){
      userRepository.save(user);
      return userOptional;
    }
    return userOptional;
  }

  @Override
  public Optional<User> deleate(UUID id) {
    Optional<User> userOptional = userRepository.findById(id);
    if (userOptional.isPresent()){
      userRepository.delete(userOptional.get());
      return userOptional;
    }
    return userOptional;
  }
}
