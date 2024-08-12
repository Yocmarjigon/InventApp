package com.application.inventApp.Controller.DTO;

import com.application.inventApp.Entity.Enums.Rol;
import com.application.inventApp.Entity.Order;
import com.application.inventApp.Entity.Sale;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {
  private UUID id;
  @NotBlank(message = "El usuario debe contar con un nombre")
  private String name;
  @NotBlank(message = "El usurio debe contar con una contraseña")
  private String password;
  @NotBlank(message = "El usuario debe contar con un rol")
  private Rol rol;
  private List<Order> orders = new ArrayList<>();
  private List<Sale> sales = new ArrayList<>();
}
