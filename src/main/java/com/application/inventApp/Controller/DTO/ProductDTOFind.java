package com.application.inventApp.Controller.DTO;

import com.application.inventApp.Entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDTOFind {
  private UUID id;
  private String name;
  private String description;
  private String price;
  private int stock;
  private Date dateAdd;
  private Category category;
  private SupplierDTOSave supplier;
}
