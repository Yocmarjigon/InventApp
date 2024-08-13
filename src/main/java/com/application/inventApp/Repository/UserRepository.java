package com.application.inventApp.Repository;

import com.application.inventApp.Entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
  Optional<User> findUserByname(String username);
}
