package com.application.inventApp.Controller.DTO.ProductsDTOs;

import com.application.inventApp.Controller.DTO.ValidationCustom.ObjectValid;
import com.application.inventApp.Entity.Category;
import com.application.inventApp.Entity.Supplier;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDTO {

  @NotBlank(message = "El producto debe contar con un nombre")
  private String name;
  @NotNull
  @DecimalMin(value = "99", inclusive = false, message = "El precio debe ser mayor o igual a 100")
  @Digits(integer = 12, fraction = 2, message = "El precio no debe contener mas de 12 digitos y 2 decimales")
  private BigDecimal price;
  private int stock;
  @ObjectValid
  private Category category;
  @ObjectValid
  private Supplier supplier;

}
