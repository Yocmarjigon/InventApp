package com.application.inventApp.Controller.DTO.ProductsDTOs;
import com.application.inventApp.Entity.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDTOFind {
  private UUID id;
  private String name;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date dateAdd;
  private String price;
  private int stock;
  private Category category;
  private SupplierProductFIndDTO supplier;
}
