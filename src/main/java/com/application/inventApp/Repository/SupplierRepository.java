package com.application.inventApp.Repository;

import com.application.inventApp.Entity.Supplier;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SupplierRepository extends CrudRepository<Supplier, UUID> {
}
