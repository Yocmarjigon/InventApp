package com.application.inventApp.Controller;

import com.application.inventApp.Controller.DTO.OrderDTO;
import com.application.inventApp.Entity.Order;
import com.application.inventApp.Services.Impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {
  @Autowired
  private OrderService orderService;

  @GetMapping("/find-all")
  public ResponseEntity<?> findAll(){
    List<OrderDTO> orders = orderService.findAll().stream().map(order -> OrderDTO.builder()
        .id(order.getId())
        .date(order.getDate())
        .state(order.getState())
        .supplier(order.getSupplier())
        .user(order.getUser())
        .build()).toList();

    return ResponseEntity.ok(orders);

  }

  @GetMapping("/find-id/{id}")
  public ResponseEntity<?> findById(@PathVariable String id){
    Optional<Order> orderOptional = orderService.findById(UUID.fromString(id));

    if(orderOptional.isPresent()){
      Order order = orderOptional.get();
      OrderDTO orderDTO = OrderDTO.builder()
          .id(order.getId())
          .date(order.getDate())
          .state(order.getState())
          .supplier(order.getSupplier())
          .user(order.getUser())
          .build();

      return ResponseEntity.ok(orderDTO);
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping("/save")
  public ResponseEntity<?> save(@RequestBody OrderDTO orderDTO){
    Order order = Order.builder()
        .date(orderDTO.getDate())
        .state(orderDTO.getState())
        .supplier(orderDTO.getSupplier())
        .user(orderDTO.getUser())
        .build();
    orderService.save(order);

    return ResponseEntity.ok("El pedido se completó correctamente");
  }

  @PutMapping("/update")
  public ResponseEntity<?> update (@PathVariable String id, @RequestBody OrderDTO orderDTO){
    Order order = Order.builder()
        .date(orderDTO.getDate())
        .state(orderDTO.getState())
        .supplier(orderDTO.getSupplier())
        .user(orderDTO.getUser())
        .build();

    Optional<Order> orderOptional = orderService.update(UUID.fromString(id), order);
    if(orderOptional.isPresent()){
      return ResponseEntity.ok("El pedido se completo correctamente");
    }
    return ResponseEntity.badRequest().build();
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> delete(@PathVariable String id){
    Optional<Order> orderOptional = orderService.delete(UUID.fromString(id));
    if (orderOptional.isPresent()){
      return ResponseEntity.ok("La venta se eliminó corrctamente");
    }
    return ResponseEntity.notFound().build();
  }
}
