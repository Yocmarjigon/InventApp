package com.application.inventApp.Controller.DTO.SaleDTOs;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDTOSaleFind {
  private UUID id;
  private String name;
  private String price;
}
