package com.application.inventApp.Controller.DTO;

import com.application.inventApp.Controller.DTO.ValidationCustom.PasswordValid;
import com.application.inventApp.Controller.DTO.ValidationCustom.RolValid;
import com.application.inventApp.Entity.Order;
import com.application.inventApp.Entity.Sale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTOUpdate {

  @NotBlank(message = "El usuario debe contar con un nombre")
  private String name;
  @PasswordValid
  private String password;
  @RolValid
  private String rol;
  private List<Order> orders = new ArrayList<>();
  private List<Sale> sales = new ArrayList<>();
}
