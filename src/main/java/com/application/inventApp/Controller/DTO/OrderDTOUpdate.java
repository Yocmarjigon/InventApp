package com.application.inventApp.Controller.DTO;

import com.application.inventApp.Controller.DTO.ValidationCustom.ObjectValid;
import com.application.inventApp.Entity.Supplier;
import com.application.inventApp.Entity.User;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDTOUpdate {
  @ObjectValid(message = "El pedido debe tener un proveedor")
  private Supplier supplier;
  @FutureOrPresent(message = "La fecha debe ser mayor o igual a la actual")
  private Date dateArrival;
}
