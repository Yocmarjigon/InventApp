package com.application.inventApp.Controller;

import com.application.inventApp.Controller.DTO.UserDTO;
import com.application.inventApp.Controller.Response.ResponseOK;
import com.application.inventApp.Entity.User;
import com.application.inventApp.Services.Impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/find-all")
  public ResponseEntity<?> findAll() {
    List<UserDTO> userDTOS = userService.findAll().stream().map(user -> UserDTO
        .builder()
        .id(user.getId())
        .name(user.getName())
        .password(user.getPassword())
        .rol(user.getRol())
        .build()
    ).toList();
    return ResponseEntity.ok(userDTOS);
  }

  @GetMapping("/find-id/{id}")
  public ResponseEntity<?> findById(@PathVariable String id) {
    Optional<User> userOptional = userService.findById(UUID.fromString(id));

    if (userOptional.isPresent()) {

      User user = userOptional.get();
      UserDTO userDTO = UserDTO.builder().
          id(user.getId())
          .name(user.getName())
          .password(user.getPassword())
          .rol(user.getRol())
          .build();

      return ResponseEntity.ok(userDTO);
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping("/save")
  public ResponseEntity<?> save(@RequestBody UserDTO userDTO) {
    if (userDTO.getName() != null || userDTO.getPassword() != null) {


      User user = User.builder()
          .id(userDTO.getId())
          .name(userDTO.getName())
          .password(userDTO.getPassword())
          .rol(userDTO.getRol())
          .build();

      userService.save(user);

      return ResponseEntity.ok(new ResponseOK("El usuario se creo correctamente"));
    }
    return ResponseEntity.badRequest().build();
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<?> update(@PathVariable String id, UserDTO userDTO){
    User user = User.builder()
        .name(userDTO.getName())
        .password(userDTO.getPassword())
        .rol(userDTO.getRol())
        .sales(userDTO.getSales())
        .orders(userDTO.getOrders())
        .build();

    Optional<User> userOptional = userService.update(UUID.fromString(id), user);

    if(userOptional.isPresent()){
      return ResponseEntity.ok(new ResponseOK("El usuario se actualizó correctamente"));
    }
    return ResponseEntity.notFound().build();
  }


  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable String id) {
      Optional<User> userOptional = userService.findById(UUID.fromString(id));
      if (userOptional.isPresent()) {
        return ResponseEntity.ok(new ResponseOK("Usuario eliminado correctamente"));
      }
      return ResponseEntity.notFound().build();
    }


}
