package com.application.inventApp.Services.Impl;

import com.application.inventApp.Entity.Order;
import com.application.inventApp.Repository.OrderRepository;
import com.application.inventApp.Services.IOrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService implements IOrderService {
  @Autowired
  private OrderRepository orderRepository;

  @Override
  public List<Order> findAll() {
    return (List<Order>) orderRepository.findAll();
  }

  @Override
  public Optional<Order> findById(UUID id) {
    return orderRepository.findById(id);
  }

  @Override
  public void save(Order order) {
    orderRepository.save(order);
  }

  @Override
  public Optional<Order> update(UUID id, Order order) {
    Optional<Order> orderOptional = orderRepository.findById(id);
    if (orderOptional.isPresent()){

      Order orderUp = Order.builder()
          .date(order.getDate())
          .user(order.getUser())
          .state(order.getState())
          .supplier(order.getSupplier())
          .build();

      orderRepository.save(orderUp);
    }


    return orderOptional;
  }

  @Override
  public Optional<Order> delete(UUID id) {
    Optional<Order> orderOptional = orderRepository.findById(id);

    if(orderOptional.isPresent()){
      orderRepository.delete(orderOptional.get());
    }
    return orderOptional;
  }
}