package com.application.inventApp.Controller;

import com.application.inventApp.Controller.DTO.SaleDTO;
import com.application.inventApp.Controller.Response.ResponseOK;
import com.application.inventApp.Entity.Sale;
import com.application.inventApp.Services.Impl.SaleService;
import com.application.inventApp.Utils.FormatDate;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
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
  @Autowired
  private FormatDate formatDate;
  
  private ModelMapper modelMapper = new ModelMapper();

  @GetMapping("/find-all")
  public ResponseEntity<?> findAll() {

    List<SaleDTO> saleDTOS = saleService.findAll().stream().map(sale -> modelMapper.map(sale, SaleDTO.class)).toList();

    return ResponseEntity.ok(saleDTOS);
  }

  @GetMapping("/find-id/{id}")
  public ResponseEntity<?> findById(@PathVariable String id) {
    Optional<Sale> saleOptional = saleService.findById(UUID.fromString(id));
    if (saleOptional.isPresent()) {

      Sale sale = saleOptional.get();

      SaleDTO saleDTO =modelMapper.map(sale, SaleDTO.class) ;
      return ResponseEntity.ok(saleDTO);
    }
    return ResponseEntity.notFound().build();

  }

  @PostMapping("/save")
  public ResponseEntity<?> save(@Valid @RequestBody SaleDTO saleDTO) {
    if (saleDTO.getPriceTotal() != null) {
      Sale sale = modelMapper.map(saleDTO, Sale.class);
      saleService.save(sale, saleDTO.getProducts());
      return ResponseEntity.ok(new ResponseOK("Venta creada correctamente"));
    }
    return ResponseEntity.badRequest().build();


  }

  @PutMapping("/update/{id}")
  public ResponseEntity<?> update(@PathVariable String id, @RequestBody SaleDTO saleDTO) {
    Sale sale = modelMapper.map(saleDTO, Sale.class);
    Optional<Sale> saleOptional = saleService.update(UUID.fromString(id), sale);
    if (saleOptional.isPresent()) {
      return ResponseEntity.ok(new ResponseOK("La venta se actualizó correctamente"));
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable String id) {
    Optional<Sale> saleOptional = saleService.delete(UUID.fromString(id));

    if (saleOptional.isPresent()) {
      return ResponseEntity.ok(new ResponseOK("La venta fue eliminada correctamente"));
    }

    return ResponseEntity.notFound().build();
  }

}
