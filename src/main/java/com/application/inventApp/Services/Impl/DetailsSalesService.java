package com.application.inventApp.Services.Impl;

import com.application.inventApp.Entity.DetailsSales;
import com.application.inventApp.Repository.DetailsSalesRepository;
import com.application.inventApp.Services.IDetailsSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DetailsSalesService implements IDetailsSaleService {
  @Autowired
  private DetailsSalesRepository detailsSalesR;


  @Override
  public List<DetailsSales> findAll() {
    return (List<DetailsSales>) detailsSalesR.findAll();
  }

  @Override
  public Optional<DetailsSales> findById(UUID id) {
    return detailsSalesR.findById(id);
  }

  @Override
  public void save(DetailsSales detailsSales) {
    detailsSalesR.save(detailsSales);
  }

  @Override
  public Optional<DetailsSales> update(UUID id, DetailsSales detailsSales) {
    Optional<DetailsSales> detailsSalesOptional = detailsSalesR.findById(id);
    if (detailsSalesOptional.isPresent()) {
      DetailsSales detailsSalesUp = DetailsSales.builder()
          .sale(detailsSales.getSale())
          .build();
      detailsSalesR.save(detailsSalesUp);
    }
    return detailsSalesOptional;
  }

  @Override
  public Optional<DetailsSales> delete(UUID id) {
    return Optional.empty();
  }
}
