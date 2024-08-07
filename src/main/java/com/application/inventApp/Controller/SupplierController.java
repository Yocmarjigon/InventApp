package com.application.inventApp.Controller;

import com.application.inventApp.Controller.DTO.SupplierDTO;
import com.application.inventApp.Entity.Supplier;
import com.application.inventApp.Services.Impl.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
  @Autowired
  private SupplierService supplierService;

  @GetMapping("/find-all")
  public ResponseEntity<?> findAll() {
    List<SupplierDTO> suppliers = supplierService.findAll().stream().map(supplier -> SupplierDTO.builder()
        .id(supplier.getId())
        .name(supplier.getName())
        .contact(supplier.getContact())
        .email(supplier.getEmail())
        .addres(supplier.getAddres())
        .products(supplier.getProducts())
        .orders(supplier.getOrders())
        .user(supplier.getUser())
        .build()).toList();
    return ResponseEntity.ok(suppliers);
  }

  @GetMapping("/find-id/{id}")
  public ResponseEntity<?> findById(@PathVariable String id) {
    Optional<Supplier> supplier = supplierService.findById(UUID.fromString(id));

    if (supplier.isPresent()) {
      Supplier supplierUp = supplier.get();
      SupplierDTO supplierDTO = SupplierDTO.builder()
          .name(supplierUp.getName())
          .contact(supplierUp.getContact())
          .email(supplierUp.getEmail())
          .addres(supplierUp.getAddres())
          .products(supplierUp.getProducts())
          .orders(supplierUp.getOrders())
          .user(supplierUp.getUser())
          .build();
      return ResponseEntity.ok(supplierDTO);
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping("/save")
  public ResponseEntity<?> save(@RequestBody SupplierDTO supplierDTO) {
    Supplier supplier = Supplier.builder()
        .name(supplierDTO.getName())
        .contact(supplierDTO.getContact())
        .email(supplierDTO.getEmail())
        .addres(supplierDTO.getAddres())
        .products(supplierDTO.getProducts())
        .orders(supplierDTO.getOrders())
        .user(supplierDTO.getUser())
        .build();
    supplierService.save(supplier);
    return ResponseEntity.ok("El proveedor se ha creado correctamente");
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<?> update(@PathVariable String id, @RequestBody SupplierDTO supplierDTO) {
    Supplier supplier = Supplier.builder()
        .name(supplierDTO.getName())
        .contact(supplierDTO.getContact())
        .email(supplierDTO.getEmail())
        .addres(supplierDTO.getAddres())
        .products(supplierDTO.getProducts())
        .orders(supplierDTO.getOrders())
        .user(supplierDTO.getUser())
        .build();

    Optional<Supplier> supplierOptional = supplierService.update(UUID.fromString(id), supplier);

    if (supplierOptional.isPresent()) {

      return ResponseEntity.ok("El proveedor se actualizó correctamente");

    }
    return ResponseEntity.notFound().build();

  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable String id) {
    Optional<Supplier> supplierOptional = supplierService.delete(UUID.fromString(id));
    if (supplierOptional.isPresent()) {
      return ResponseEntity.ok("El proveedor se eliminó correctamente");
    }
    return ResponseEntity.notFound().build();
  }


}
