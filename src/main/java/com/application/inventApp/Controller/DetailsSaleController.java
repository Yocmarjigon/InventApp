package com.application.inventApp.Controller;

import com.application.inventApp.Controller.DTO.DetailsSaleDTO;
import com.application.inventApp.Entity.DetailsSales;
import com.application.inventApp.Services.Impl.DetailsSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/detalle-venta")
public class DetailsSaleController {
  @Autowired
  private DetailsSalesService detailsSalesService;

  @GetMapping("/find-all")
  public ResponseEntity<?> findAll(){
    List<DetailsSaleDTO> detailsSaleDTOS = detailsSalesService.findAll().stream().map(detailsSales -> DetailsSaleDTO.builder()
        .id(detailsSales.getId())
        .sale(detailsSales.getSale())
        .build()).toList();
    return ResponseEntity.ok(detailsSaleDTOS);
  }

  @GetMapping("/find-id/{id}")
  public ResponseEntity<?> findById(@PathVariable String id){
    Optional<DetailsSales> detailsSalesOptional = detailsSalesService.findById(UUID.fromString(id));
    if (detailsSalesOptional.isPresent()){
      DetailsSales detailsSales = detailsSalesOptional.get();
      DetailsSaleDTO detailsSaleDTO = DetailsSaleDTO.builder()
          .id(detailsSales.getId())
          .sale(detailsSales.getSale())
          .build();

      return ResponseEntity.ok(detailsSaleDTO);
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping("/save")
  public ResponseEntity<?> save(@RequestBody DetailsSaleDTO detailsSaleDTO){
    DetailsSales detailsSales = DetailsSales.builder()
        .sale(detailsSaleDTO.getSale())
        .build();
    detailsSalesService.save(detailsSales);
    return ResponseEntity.ok("El detalle de la venta se creo correctamente");
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<?> update(@PathVariable String id, @RequestBody DetailsSaleDTO detailsSaleDTO){
    DetailsSales detailsSales = DetailsSales.builder()
        .sale(detailsSaleDTO.getSale())
        .build();
    Optional<DetailsSales> detailsSalesOptional = detailsSalesService.update(UUID.fromString(id), detailsSales);
    if(detailsSalesOptional.isPresent()){
      return ResponseEntity.ok("El detalle de la venta se actualizo correctamente");
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable String id){
    Optional<DetailsSales> detailsSalesOptional = detailsSalesService.delete(UUID.fromString(id));

    if (detailsSalesOptional.isPresent()){
      return ResponseEntity.ok("El detalle de la venta se elimino correctamente");
    }
    return ResponseEntity.notFound().build();
  }

}
