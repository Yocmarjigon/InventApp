package com.application.inventApp.Controller;

import com.application.inventApp.Controller.DTO.ProductsDTOs.ProductDTO;
import com.application.inventApp.Controller.DTO.ProductsDTOs.ProductDTOFind;
import com.application.inventApp.Controller.DTO.SaleDTOs.ProductDTOUpdate;
import com.application.inventApp.Controller.Response.ResponseOK;
import com.application.inventApp.Entity.Product;
import com.application.inventApp.Services.Impl.ProductService;
import com.application.inventApp.Utils.Format;
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
@RequestMapping("/product")
public class ProductController {
  @Autowired
  private ProductService productService;
  @Autowired
  private Format format;
  private ModelMapper modelMapper = new ModelMapper();

  @GetMapping("/find-all")
  public List<?> findAll() {
    try {
      List<ProductDTOFind> products = productService.findAll().stream().map(product -> {
        ProductDTOFind productDTOFind = modelMapper.map(product, ProductDTOFind.class);
        productDTOFind.setPrice(this.format.formaterMoney(product.getPrice()));
        productDTOFind.setDateAdd(format.formaterDate(product.getDateAdd()));
        return productDTOFind;
      }).toList();
      return products;
    } catch (Exception e) {
      System.out.println(e);
      throw e;
    }
  }

  @GetMapping("/find-id/{id}")
  public ResponseEntity<?> findById(@PathVariable String id) {
    try {

      Optional<Product> productOptional = productService.findById(UUID.fromString(id));
      if (productOptional.isPresent()) {
        Product product = productOptional.get();
        ProductDTOFind productDTO = modelMapper.map(product, ProductDTOFind.class);

        return ResponseEntity.ok(productDTO);
      }
      return ResponseEntity.notFound().build();

    } catch (Exception e) {
      throw e;
    }
  }

  @PostMapping("/save")
  public ResponseEntity<?> save(@Valid @RequestBody ProductDTO productDTO, BindingResult bindingResult) {
    try{
      if (bindingResult.hasErrors()) {
        return new ResponseEntity<>(new ResponseOK(bindingResult.getFieldError().getDefaultMessage()), HttpStatus.BAD_REQUEST);
      }
      Product product = modelMapper.map(productDTO, Product.class);
      productService.save(product);
      return ResponseEntity.ok(new ResponseOK("El producto se creo correctamente"));
    }catch (Exception e){
      throw e;
    }

  }

  @PutMapping("/update/{id}")
  public ResponseEntity<?> update(@PathVariable String id, @RequestBody ProductDTOUpdate productDTO) {
    try{
      Product product = modelMapper.map(productDTO, Product.class);

      Optional<Product> productOptional = productService.update(UUID.fromString(id), product);
      if (productOptional.isPresent()) {
        return ResponseEntity.ok(new ResponseOK("El producto se actualizo correctamente"));
      }
      return ResponseEntity.notFound().build();
    }catch (Exception e){
      throw e;
    }
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable String id) {
    try{
      Optional<Product> productOptional = productService.delete(UUID.fromString(id));
      if (productOptional.isPresent()) {
        return ResponseEntity.ok(new ResponseOK("El producto se elimino correctamente"));
      }
      return ResponseEntity.notFound().build();
    }catch (Exception e){
      throw e;
    }

  }
}
