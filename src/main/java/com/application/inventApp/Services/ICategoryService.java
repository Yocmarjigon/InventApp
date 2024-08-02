package com.application.inventApp.Services;

import com.application.inventApp.Entity.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICategoryService {
  List<Category> findAll();
  Optional<Category> findById(UUID id);
  void save (Category category);
  Optional<Category> update(UUID id, Category category);
  Optional<Category> delete(UUID id);
}
