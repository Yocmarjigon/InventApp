package com.application.inventApp.Controller.DTO;

import com.application.inventApp.Entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
  private String name;
  @JsonIgnore
  private List<Product> products = new ArrayList<>();
}
