package com.application.inventApp.Services;

import com.application.inventApp.Entity.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IOrderService {

  List<Order> findAll();
  Optional<Order> findById(UUID id);
  void save(Order order);
  Optional<Order> update(UUID id, Order order);
  Optional<Order> delete(UUID id);

}
