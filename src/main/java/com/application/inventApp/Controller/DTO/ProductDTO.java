package com.application.inventApp.Controller.DTO;

import com.application.inventApp.Entity.Category;
import com.application.inventApp.Entity.Supplier;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class ProductDTO {
  private UUID id;
  private String name;
  private String description;
  private BigDecimal price;
  private int stock;
  private Date dateAdd;
  private Category category;
  @JsonIgnore 
  private Supplier supplier;
}
