package com.application.inventApp.Controller.DTO.OrderDTOs;

import com.application.inventApp.Controller.DTO.SupplierDTOs.SupplierDTOSave;
import com.application.inventApp.Entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDTOFind {
  private UUID id;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date date;
  private SupplierDTOSave supplier;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date dateArrival;
  private User user;
}
