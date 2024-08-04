package com.application.inventApp.Controller.DTO;

import com.application.inventApp.Entity.Enums.State;
import com.application.inventApp.Entity.Supplier;
import com.application.inventApp.Entity.User;
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
public class OrderDTO {

  private UUID id;
  private Date date;
  private State state;
  private Supplier supplier;
  private User user;
}
