package com.application.inventApp.Services.Impl;

import com.application.inventApp.Entity.Category;
import com.application.inventApp.Repository.CategoryRepository;
import com.application.inventApp.Services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService implements ICategoryService {
  @Autowired
  private CategoryRepository categoryRepository;


  @Override
  public List<Category> findAll() {
    return (List<Category>) categoryRepository.findAll();
  }

  @Override
  public Optional<Category> findById(UUID id) {
    return categoryRepository.findById(id);
  }

  @Override
  public void save(Category category) {
    categoryRepository.save(category);
  }

  @Override
  public Optional<Category> update(UUID id, Category category) {
    Optional<Category> optionalCategory = categoryRepository.findById(id);
    if(optionalCategory.isPresent()){
      Category categoryUp = Category.builder()
          .name(category.getName())
          .products(category.getProducts())
          .build();
      categoryRepository.save(categoryUp);
    }

    return optionalCategory;
  }

  @Override
  public Optional<Category> delete(UUID id) {
    Optional<Category> categoryOptional = categoryRepository.findById(id);
    if (categoryOptional.isPresent()){
      categoryRepository.delete(categoryOptional.get());
    }

    return categoryOptional;
  }
}
