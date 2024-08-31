package com.application.inventApp.Controller.DTO.ProductsDTOs;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SupplierProductFIndDTO {
  private UUID id;
  private String name;
  private String contact;
}
