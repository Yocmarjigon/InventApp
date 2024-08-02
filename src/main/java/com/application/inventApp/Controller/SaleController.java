package com.application.inventApp.Controller;

import com.application.inventApp.Controller.DTO.SaleDTO;
import com.application.inventApp.Controller.Response.ResponseOK;
import com.application.inventApp.Entity.Sale;
import com.application.inventApp.Services.Impl.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/sale")
public class SaleController {
  @Autowired
  private SaleService saleService;

  @GetMapping("/find-all")
  public ResponseEntity<?> findAll() {

    List<SaleDTO> saleDTOS = saleService.findAll().stream().map(sale -> SaleDTO.builder()
        .id(sale.getId())
        .date(sale.getDate())
        .priceTotal(sale.getPriceTotal())
        .user(sale.getUser())
        .detailsSales(sale.getDetailsSales())
        .build()
    ).toList();

    return ResponseEntity.ok(saleDTOS);
  }

  @GetMapping("/find-id/{id}")
  public ResponseEntity<?> findById(@PathVariable String id) {
    Optional<Sale> saleOptional = saleService.findById(UUID.fromString(id));
    if (saleOptional.isPresent()) {

      Sale sale = saleOptional.get();

      SaleDTO saleDTO = SaleDTO.builder()
          .id(sale.getId())
          .date(sale.getDate())
          .priceTotal(sale.getPriceTotal())
          .user(sale.getUser())
          .detailsSales(sale.getDetailsSales())
          .build();
      return ResponseEntity.ok(saleDTO);
    }
    return ResponseEntity.notFound().build();

  }

  @PostMapping("/save")
  public ResponseEntity<?> save(@RequestBody SaleDTO saleDTO) {
    if (saleDTO.getPriceTotal() != null) {
      Sale sale = Sale.builder()
          .date(saleDTO.getDate())
          .priceTotal(saleDTO.getPriceTotal())
          .user(saleDTO.getUser())
          .detailsSales(saleDTO.getDetailsSales())
          .build();
      return ResponseEntity.ok(new ResponseOK("Venta creada correctamente"));
    }
    return ResponseEntity.badRequest().build();


  }

  @PutMapping("/update/{id}")
  public ResponseEntity<?> update(@PathVariable String id, @RequestBody SaleDTO saleDTO) {
    Sale sale = Sale.builder()
        .priceTotal(saleDTO.getPriceTotal())
        .build();
    Optional<Sale> saleOptional = saleService.update(UUID.fromString(id), sale);
    if (saleOptional.isPresent()){
      return ResponseEntity.ok("La venta se actualizó correctamente");
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable String id) {
    Optional<Sale> saleOptional = saleService.delete(UUID.fromString(id));

    if(saleOptional.isPresent()){
      return ResponseEntity.ok("La venta fue eliminada correctamente");
    }

    return ResponseEntity.notFound().build();
  }

}
