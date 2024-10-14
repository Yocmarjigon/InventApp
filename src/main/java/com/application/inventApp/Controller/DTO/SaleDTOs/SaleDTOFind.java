package com.application.inventApp.Controller.DTO.SaleDTOs;




import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.application.inventApp.Controller.DTO.UserDTOs.UserDTOFind;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SaleDTOFind {
  private UUID id;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date date;
  private String priceTotal;
  private List<ProductDTOSaleFind> products;
  private UserDTOFind user;
}
