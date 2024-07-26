package com.application.inventApp.Repository;

import com.application.inventApp.Entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CategoryRepository extends CrudRepository<Category, UUID> {
}
