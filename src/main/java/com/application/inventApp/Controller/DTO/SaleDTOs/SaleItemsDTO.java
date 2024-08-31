package com.application.inventApp.Controller.DTO.SaleDTOs;

import com.application.inventApp.Controller.DTO.ValidationCustom.NumberSaleValid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SaleItemsDTO {
  @NumberSaleValid(message = "El producto debe tener un item para la compra")
  private List<ProductsSoldDTO> products;

}
