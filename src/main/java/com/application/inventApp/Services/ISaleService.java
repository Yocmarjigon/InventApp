package com.application.inventApp.Services;

import com.application.inventApp.Entity.Product;
import com.application.inventApp.Entity.Sale;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ISaleService {

  List<Sale> findAll();
  Optional<Sale> findById(UUID id);
  Optional<Sale> delete(UUID id);
  void save(Sale sale,List<Product> product );
  Optional<Sale> update(UUID id, Sale sale);
  
}
