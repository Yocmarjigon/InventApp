package com.application.inventApp.Services;

import com.application.inventApp.Controller.DTO.SaleDTOs.ProductsSoldDTO;
import com.application.inventApp.Entity.Sale;
import com.application.inventApp.Exception.NotFoundException;
import com.application.inventApp.Exception.StockException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ISaleService {

  List<Sale> findAll();
  Optional<Sale> findById(UUID id);
  Optional<Sale> delete(UUID id);
  void save(List<ProductsSoldDTO>  productsSoldDTOS ) throws StockException, NotFoundException;
  Optional<Sale> update(UUID id, Sale sale);
  
}
