package com.application.inventApp.Controller.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthUserRequest {
  @NotBlank(message = "El nombre del usuario no debe estar vacio")
  private String name;
  @NotBlank(message = "La contraseña no debe estar vacia")
  private String password;
}
