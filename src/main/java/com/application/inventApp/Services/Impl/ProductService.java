package com.application.inventApp.Services.Impl;

import com.application.inventApp.Entity.Product;
import com.application.inventApp.Repository.ProductRepository;
import com.application.inventApp.Services.IProductService;
import com.application.inventApp.Utils.FormatDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService implements IProductService {
  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private FormatDate formatDate;

  @Override
  public List<Product> findAll() {
    return (List<Product>) productRepository.findAll();
  }

  @Override
  public Optional<Product> findById(UUID id) {
    return productRepository.findById(id);
  }

  @Override
  public void save(Product product) {
    product.setDateAdd(formatDate.getDateFormat());
    productRepository.save(product);
  }

  @Override
  public Optional<Product> update(UUID id, Product product) {
    Optional<Product> productOptional = productRepository.findById(id);
    if (productOptional.isPresent()){
      Product productUp =productOptional.get();
          productUp.setName(product.getName());
          productUp.setDescription(product.getDescription());
          productUp.setPrice(product.getPrice());
          productUp.setStock(product.getStock());
          productUp.setDateAdd(product.getDateAdd());
          productUp.setCategory(product.getCategory());
          productUp.setSupplier(product.getSupplier());
      productRepository.save(productUp);
    }
    return productOptional;
  }

  @Override
  public Optional<Product> delete(UUID id) {
    Optional<Product> productOptional  = productRepository.findById(id);
    if(productOptional.isPresent()){
      productRepository.delete(productOptional.get());
    }

    return productOptional;
  }
}
