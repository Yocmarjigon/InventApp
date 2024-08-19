package com.application.inventApp.Controller.DTO;

import com.application.inventApp.Controller.DTO.ValidationCustom.ListObjectValid;
import com.application.inventApp.Entity.Product;
import com.application.inventApp.Entity.User;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SaleDTOSave {
  @Min(value = 1, message = "El precio total debe tener 1 como valor minimo")
  private BigDecimal priceTotal;
  @ListObjectValid
  private List<Product> products;
  private User user;


}
