package com.application.inventApp.Controller;

import com.application.inventApp.Controller.DTO.OrderDTO;
import com.application.inventApp.Controller.Response.ResponseOK;
import com.application.inventApp.Entity.Order;
import com.application.inventApp.Services.Impl.OrderService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {
  @Autowired
  private OrderService orderService;

  private ModelMapper modelMapper = new ModelMapper();
  @GetMapping("/find-all")
  public ResponseEntity<?> findAll(){
    List<OrderDTO> orders = orderService.findAll().stream().map(order -> modelMapper.map(order, OrderDTO.class) ).toList();

    return ResponseEntity.ok(orders);

  }

  @GetMapping("/find-id/{id}")
  public ResponseEntity<?> findById(@PathVariable String id){
    Optional<Order> orderOptional = orderService.findById(UUID.fromString(id));

    if(orderOptional.isPresent()){
      Order order = orderOptional.get();
      OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);

      return ResponseEntity.ok(orderDTO);
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping("/save")
  public ResponseEntity<?> save(@Valid @RequestBody OrderDTO orderDTO, BindingResult bindingResult){
    if (bindingResult.hasErrors()){
      return new ResponseEntity<>(new ResponseOK(bindingResult.getFieldError().getDefaultMessage()), HttpStatus.BAD_REQUEST);
    }
    Order order = modelMapper.map(orderDTO, Order.class);
    orderService.save(order);

    return ResponseEntity.ok(new ResponseOK("El pedido se completó correctamente"));
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<?> update (@PathVariable String id, @RequestBody OrderDTO orderDTO){
    Order order = modelMapper.map(orderDTO, Order.class);

    Optional<Order> orderOptional = orderService.update(UUID.fromString(id), order);
    if(orderOptional.isPresent()){
      return ResponseEntity.ok(new ResponseOK("El pedido se actualisó correctamente"));
    }
    return ResponseEntity.badRequest().build();
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable String id){
    Optional<Order> orderOptional = orderService.delete(UUID.fromString(id));
    if (orderOptional.isPresent()){
      return ResponseEntity.ok(new ResponseOK("El pedido se eliminó corrctamente"));
    }
    return ResponseEntity.notFound().build();
  }
}
