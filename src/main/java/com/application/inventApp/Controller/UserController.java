package com.application.inventApp.Controller;

import com.application.inventApp.Controller.DTO.UserDTO;
import com.application.inventApp.Controller.Response.ResponseOK;
import com.application.inventApp.Entity.User;
import com.application.inventApp.Services.Impl.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  private ModelMapper modelMapper = new ModelMapper();

  @GetMapping("/find-all")
  public ResponseEntity<?> findAll() {
    List<UserDTO> userDTOS = userService.findAll().stream().map(user -> modelMapper.map(user, UserDTO.class)
    ).toList();
    return ResponseEntity.ok(userDTOS);
  }

  @GetMapping("/find-id/{id}")
  public ResponseEntity<?> findById(@PathVariable String id) {
    Optional<User> userOptional = userService.findById(UUID.fromString(id));

    if (userOptional.isPresent()) {

      User user = userOptional.get();
      UserDTO userDTO = modelMapper.map(user, UserDTO.class);

      return ResponseEntity.ok(userDTO);
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping("/save")
  public ResponseEntity<?> save(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {

    if (bindingResult.hasErrors()){

    }

      User user = modelMapper.map(userDTO, User.class);
      userService.save(user);

      return ResponseEntity.ok(new ResponseOK("El usuario se creo correctamente"));
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<?> update(@PathVariable String id, @RequestBody UserDTO userDTO){
    User user = modelMapper.map(userDTO, User.class);

    Optional<User> userOptional = userService.update(UUID.fromString(id), user);

    if(userOptional.isPresent()){
      return ResponseEntity.ok(new ResponseOK("El usuario se actualizó correctamente"));
    }
    return ResponseEntity.notFound().build();
  }


  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable String id) {
      Optional<User> userOptional = userService.deleate(UUID.fromString(id));
      if (userOptional.isPresent()) {
        return ResponseEntity.ok(new ResponseOK("Usuario eliminado correctamente"));
      }
      return ResponseEntity.notFound().build();
    }


}
