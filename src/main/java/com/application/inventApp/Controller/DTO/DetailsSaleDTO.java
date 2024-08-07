package com.application.inventApp.Controller.DTO;

import com.application.inventApp.Entity.Sale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DetailsSaleDTO {
  private UUID id;
  private Sale sale;
}
