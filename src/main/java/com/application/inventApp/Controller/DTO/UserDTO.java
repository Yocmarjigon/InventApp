package com.application.inventApp.Controller.DTO;

import com.application.inventApp.Entity.Enums.Rol;
import com.application.inventApp.Entity.Order;
import com.application.inventApp.Entity.Sale;
import jakarta.persistence.*;
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
  private String name;
  private String password;
  private Rol rol;
  private List<Order> orders = new ArrayList<>();
  private List<Sale> sales = new ArrayList<>();

}
