package com.application.inventApp.Controller;

import com.application.inventApp.Controller.DTO.ProductDTO;
import com.application.inventApp.Controller.Response.ResponseOK;
import com.application.inventApp.Entity.Product;
import com.application.inventApp.Services.Impl.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {
  @Autowired
  private ProductService productService;
  private ModelMapper modelMapper = new ModelMapper();

  @GetMapping("/find-all")
  public List<ProductDTO> findAll(){
    List<ProductDTO> products = productService.findAll().stream().map(product -> modelMapper.map(product, ProductDTO.class)).toList();
    System.out.println(products);
    return products;
  }

  @GetMapping("/find-if/{id}")
  public ResponseEntity<?> findById(@PathVariable String id){
    Optional<Product> productOptional = productService.findById(UUID.fromString(id));
    if(productOptional.isPresent()){
      Product product = productOptional.get();
      ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);

      return ResponseEntity.ok(productDTO);
    }
    return ResponseEntity.notFound().build();

  }

  @PostMapping("/save")
  public ResponseEntity<?> save(@RequestBody ProductDTO productDTO){
    Product product = modelMapper.map(productDTO, Product.class);
    productService.save(product);
    return ResponseEntity.ok(new ResponseOK("El producto se creo correctamente"));
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<?> update(@PathVariable String id, @RequestBody ProductDTO productDTO){
    Product product = modelMapper.map(productDTO, Product.class);

    Optional<Product> productOptional = productService.update(UUID.fromString(id), product);
    if (productOptional.isPresent()){
      return ResponseEntity.ok(new ResponseOK("El producto se actualizo correctamente"));
    }
    return ResponseEntity.notFound().build();

  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable String id){
    Optional<Product> productOptional = productService.delete(UUID.fromString(id));
    if(productOptional.isPresent()){
      return ResponseEntity.ok(new ResponseOK("El producto se elimino correctamente"));
    }
    return ResponseEntity.notFound().build();
  }
}
