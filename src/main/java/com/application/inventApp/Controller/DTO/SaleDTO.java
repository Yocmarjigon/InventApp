package com.application.inventApp.Controller.DTO;

import com.application.inventApp.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SaleDTO {

  private UUID id;
  private Date date;
  private BigDecimal priceTotal;
  private User user;

}
