package com.application.inventApp.Controller.DTO;

import com.application.inventApp.Entity.Product;
import com.application.inventApp.Entity.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SaleDTOFind {
  private UUID id;
  private Date date;
  @Min(value = 1, message = "El precio total debe tener 1 como valor minimo")
  private BigDecimal priceTotal;
  @Size(min = 1, message = "La venta debe tener por lo menos un producto")
  private List<Product> products;
  private User user;
}
