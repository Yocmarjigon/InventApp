package com.application.inventApp.Controller.DTO.ProductsDTOs;
import com.application.inventApp.Entity.Category;
import com.application.inventApp.Entity.Supplier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
  private Category category;
  private SupplierProductFIndDTO supplier;
}
