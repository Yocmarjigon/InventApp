package com.application.inventApp.Services;

import com.application.inventApp.Entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProductService {
  List<Product> findAll();
  Optional<Product> findById(UUID id);
  void save (Product product);
  Optional<Product> update(UUID id, Product product);
  Optional<Product> delete(UUID id);
}
