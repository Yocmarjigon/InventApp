package com.application.inventApp.Controller.DTO;

import com.application.inventApp.Entity.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
  @NotBlank(message = "El producto debe contar con un nombre")
  private String name;
  @Size(min = 100, max = 400, message = "La descripción tener como minimo 100 caracteres y maximo 400")
  private String description;
  @Min(value = 1, message = "El precio debe tener 1 como valor minimo")
  private BigDecimal price;
  private int stock;
  private Date dateAdd;
  @NotBlank(message = "El producto debe contar con una categoria")
  private Category category;
}
