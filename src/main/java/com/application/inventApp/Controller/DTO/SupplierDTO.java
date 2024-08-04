package com.application.inventApp.Controller.DTO;

import com.application.inventApp.Entity.Order;
import com.application.inventApp.Entity.Product;
import com.application.inventApp.Entity.User;
import jakarta.persistence.*;
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
public class SupplierDTO {

  private UUID id;
  private String name;
  private String contact;
  private String email;
  private String addres;
  private List<Product> products = new ArrayList<>();
  private List<Order> orders = new ArrayList<>();
  private User user;

}
