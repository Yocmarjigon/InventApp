package com.application.inventApp.Controller;

import com.application.inventApp.Controller.DTO.OrderDTOs.OrderDTOFind;
import com.application.inventApp.Controller.DTO.OrderDTOs.OrderDTOSave;
import com.application.inventApp.Controller.Response.ResponseOK;
import com.application.inventApp.Entity.Order;
import com.application.inventApp.Exception.NotFoundException;
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
    try {
      List<OrderDTOFind> orders = orderService.findAll().stream().map(order -> modelMapper.map(order, OrderDTOFind.class) ).toList();

      return ResponseEntity.ok(orders);
    }catch (Exception e){
      throw e;
    }


  }

  @GetMapping("/find-id/{id}")
  public ResponseEntity<?> findById(@PathVariable String id){
    try{
      Optional<Order> orderOptional = orderService.findById(UUID.fromString(id));

      if(orderOptional.isPresent()){
        Order order = orderOptional.get();
        OrderDTOFind orderDTO = modelMapper.map(order, OrderDTOFind.class);

        return ResponseEntity.ok(orderDTO);
      }
      return ResponseEntity.notFound().build();
    }catch (Exception e){
      throw e;
    }

  }

  @PostMapping("/save")
  public ResponseEntity<?> save(@Valid @RequestBody OrderDTOSave orderDTO, BindingResult bindingResult) throws NotFoundException {
    try{
      if (bindingResult.hasErrors()){
        return new ResponseEntity<>(new ResponseOK(bindingResult.getFieldError().getDefaultMessage()), HttpStatus.BAD_REQUEST);
      }
      Order order = modelMapper.map(orderDTO, Order.class);
      orderService.save(order);

      return ResponseEntity.ok(new ResponseOK("El pedido se completó correctamente"));
    }catch (Exception e){
      throw e;
    }

  }

  @PutMapping("/update/{id}")
  public ResponseEntity<?> update (@PathVariable String id, @RequestBody OrderDTOSave orderDTO){
    try{
      Order order = modelMapper.map(orderDTO, Order.class);

      Optional<Order> orderOptional = orderService.update(UUID.fromString(id), order);
      if(orderOptional.isPresent()){
        return ResponseEntity.ok(new ResponseOK("El pedido se actualisó correctamente"));
      }
      return ResponseEntity.badRequest().build();
    }catch (Exception e){
      throw e;
    }

  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable String id){
    try{
      Optional<Order> orderOptional = orderService.delete(UUID.fromString(id));
      if (orderOptional.isPresent()){
        return ResponseEntity.ok(new ResponseOK("El pedido se eliminó corrctamente"));
      }
      return ResponseEntity.notFound().build();
    }catch (Exception e){
      throw e;
    }

  }
}
