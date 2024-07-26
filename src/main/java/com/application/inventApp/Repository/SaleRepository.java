package com.application.inventApp.Repository;

import com.application.inventApp.Entity.Sale;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SaleRepository extends CrudRepository<Sale, UUID> {
}
