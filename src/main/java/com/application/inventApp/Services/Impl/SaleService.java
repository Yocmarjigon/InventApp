package com.application.inventApp.Services.Impl;

import com.application.inventApp.Entity.Sale;
import com.application.inventApp.Repository.SaleRepository;
import com.application.inventApp.Services.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SaleService implements ISaleService {
  @Autowired
  private SaleRepository saleRepository;


  @Override
  public List<Sale> findAll() {
    return (List<Sale>) saleRepository.findAll();
  }

  @Override
  public Optional<Sale> findById(UUID id) {
    return saleRepository.findById(id);
  }

  @Override
  public Optional<Sale> delete(UUID id) {
    Optional<Sale> optionalSale = saleRepository.findById(id);
    if(optionalSale.isPresent()){
      saleRepository.delete(optionalSale.get());
      return optionalSale;
    }

    return optionalSale;
  }

  @Override
  public void save(Sale sale) {
    saleRepository.save(sale);
  }

  @Override
  public Optional<Sale> update(UUID id, Sale sale) {
    Optional<Sale> saleOptional = saleRepository.findById(id);
    if (saleOptional.isPresent()){
      Sale saleUp = saleOptional.get();

      saleUp.setDetailsSales(sale.getDetailsSales());
      saleUp.setDate(sale.getDate());
      saleUp.setUser(sale.getUser());
      saleUp.setPriceTotal(sale.getPriceTotal());

      saleRepository.save(saleUp);
    }
    return saleOptional;
  }

}

