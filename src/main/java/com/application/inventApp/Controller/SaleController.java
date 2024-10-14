package com.application.inventApp.Controller;

import com.application.inventApp.Controller.DTO.SaleDTOs.ProductDTOSaleFind;
import com.application.inventApp.Controller.DTO.SaleDTOs.SaleDTOFind;
import com.application.inventApp.Controller.DTO.SaleDTOs.SaleDTOUpdate;
import com.application.inventApp.Controller.DTO.SaleDTOs.SaleItemsDTO;
import com.application.inventApp.Controller.Response.ResponseOK;
import com.application.inventApp.Entity.Sale;
import com.application.inventApp.Exception.NotFoundException;
import com.application.inventApp.Exception.StockException;
import com.application.inventApp.Services.Impl.SaleService;

import com.application.inventApp.Utils.Format;
import com.auth0.jwt.exceptions.JWTVerificationException;
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
@RequestMapping("/sale")
public class SaleController {
  @Autowired
  private SaleService saleService;
  @Autowired
  private Format format;
  private ModelMapper modelMapper = new ModelMapper();

  @GetMapping("/find-all")
  public ResponseEntity<?> findAll() throws JWTVerificationException {
    try {

      List<SaleDTOFind> saleDTOS = saleService.findAll().stream().map(sale -> {
        SaleDTOFind saleDTOFind = modelMapper.map(sale, SaleDTOFind.class);

        saleDTOFind.setProducts(sale.getProducts().stream().map(product -> {
          ProductDTOSaleFind productDTOSaleFind = modelMapper.map(product, ProductDTOSaleFind.class);
          productDTOSaleFind.setPrice(format.formaterMoney(product.getPrice()));
          return productDTOSaleFind;
        }).toList());


        saleDTOFind.setPriceTotal(format.formaterMoney(sale.getPriceTotal()));
        return saleDTOFind;
      }).toList();

      return ResponseEntity.ok(saleDTOS);
    } catch (Exception e) {
      throw e;
    }

  }

  @GetMapping("/find-id/{id}")
  public ResponseEntity<?> findById(@PathVariable String id) {
    try {
      Optional<Sale> saleOptional = saleService.findById(UUID.fromString(id));
      if (saleOptional.isPresent()) {

        Sale sale = saleOptional.get();

        SaleDTOFind saleDTO = modelMapper.map(sale, SaleDTOFind.class);
        return ResponseEntity.ok(saleDTO);
      }
      return ResponseEntity.notFound().build();

    } catch (Exception e) {
      throw e;
    }
  }

  @PostMapping("/save")
  public ResponseEntity<?> save(@Valid @RequestBody SaleItemsDTO saleDTO, BindingResult bindingResult) throws StockException, NotFoundException {
    try {
      if (bindingResult.hasErrors()) {
        return new ResponseEntity<>(new ResponseOK(bindingResult.getFieldError().getDefaultMessage()), HttpStatus.BAD_REQUEST);
      }

      saleService.save(saleDTO.getProducts());
      return ResponseEntity.ok(new ResponseOK("Venta creada correctamente"));

    } catch (Exception e) {
      throw e;
    }

  }

  @PutMapping("/update/{id}")
  public ResponseEntity<?> update(@PathVariable String id, @RequestBody SaleDTOUpdate saleDTO) {
    try {
      Sale sale = modelMapper.map(saleDTO, Sale.class);
      Optional<Sale> saleOptional = saleService.update(UUID.fromString(id), sale);
      if (saleOptional.isPresent()) {
        return ResponseEntity.ok(new ResponseOK("La venta se actualizó correctamente"));
      }
      return ResponseEntity.notFound().build();
    } catch (Exception e) {
      throw e;
    }
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable String id) {
    try {
      Optional<Sale> saleOptional = saleService.delete(UUID.fromString(id));

      if (saleOptional.isPresent()) {
        return ResponseEntity.ok(new ResponseOK("La venta fue eliminada correctamente"));
      }

      return ResponseEntity.notFound().build();
    } catch (Exception e) {
      throw e;
    }

  }

}
