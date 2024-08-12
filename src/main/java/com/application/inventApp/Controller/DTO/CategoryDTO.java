package com.application.inventApp.Controller.DTO;

import com.application.inventApp.Entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class CategoryDTO {
  private UUID id;
  @NotBlank(message = "La categoria debe contar con un nombre")
  private String name;
  @JsonIgnore
  private List<Product> products = new ArrayList<>();
}
