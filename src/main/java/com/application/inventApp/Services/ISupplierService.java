package com.application.inventApp.Services;

import com.application.inventApp.Entity.Supplier;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ISupplierService {
  List<Supplier> findAll();
  Optional<Supplier> findById(UUID id);
  void save(Supplier supplier);
  Optional<Supplier> update(UUID id, Supplier supplier);
  Optional<Supplier> delete(UUID id);
}
