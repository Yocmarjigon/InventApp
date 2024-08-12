package com.application.inventApp.Controller.DTO;

import com.application.inventApp.Entity.Enums.State;
import com.application.inventApp.Entity.Supplier;
import com.application.inventApp.Entity.User;
import jakarta.validation.constraints.NotBlank;
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
public class OrderDTO {

  private UUID id;
  private Date date;
  @NotBlank(message = "El pedido debe tener un proveedor")
  private Supplier supplier;
  @NotBlank(message = "El pedido debe contar con una fecha de llegada")
  private Date dateArrived;
  private User user;
}
