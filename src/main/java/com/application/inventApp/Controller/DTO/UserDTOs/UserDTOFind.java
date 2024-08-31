package com.application.inventApp.Controller.DTO.UserDTOs;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTOFind {
  private UUID id;
  private String name;
  private String rol;
}
