package com.application.inventApp.Services;

import com.application.inventApp.Entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserService {
  List<User> findAll();
  Optional<User> findById(UUID id);
  void save(User user);
  Optional<User> update(UUID id, User user);
  Optional<User> deleate(UUID id);
}
