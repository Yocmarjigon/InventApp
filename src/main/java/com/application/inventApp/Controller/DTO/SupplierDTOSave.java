package com.application.inventApp.Controller.DTO;

import com.application.inventApp.Entity.Order;
import com.application.inventApp.Entity.Product;
import com.application.inventApp.Entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
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
public class SupplierDTOSave {

  @NotBlank(message = "El proveedor debe contar con un nombre")
  private String name;
  @NotBlank(message = "El proveedor debe tener un contact")
  private String contact;
  @Email(message = "El proveedor debe contar con un correo")
  private String email;

}
