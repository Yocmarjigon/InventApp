package com.application.inventApp.Controller.DTO.SaleDTOs;

import com.application.inventApp.Entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductsSoldDTO {
  private int numberSale;
  private Product product;
}
