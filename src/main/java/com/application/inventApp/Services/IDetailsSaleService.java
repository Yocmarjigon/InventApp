package com.application.inventApp.Services;

import com.application.inventApp.Entity.DetailsSales;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IDetailsSaleService {
  List<DetailsSales> findAll();
  Optional<DetailsSales> findById(UUID id);
  void save(DetailsSales detailsSales);
  Optional<DetailsSales> update(UUID id, DetailsSales detailsSales);
  Optional<DetailsSales> delete(UUID id);
}
