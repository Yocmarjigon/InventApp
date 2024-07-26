package com.application.inventApp.Repository;

import com.application.inventApp.Entity.DetailsSales;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface DetailsSalesRepository extends CrudRepository<DetailsSales, UUID> {
}
