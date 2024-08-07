package com.application.inventApp.Controller;

import com.application.inventApp.Controller.DTO.CategoryDTO;
import com.application.inventApp.Entity.Category;
import com.application.inventApp.Services.Impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/categoria")
public class CategoryController {
  @Autowired
  private CategoryService categoryService;

  @GetMapping("/find-all")
  public ResponseEntity<?> findAll(){
    List<CategoryDTO> categories = categoryService.findAll().stream().map(category -> CategoryDTO.builder()
        .id(category.getId())
        .name(category.getName())
        .products(category.getProducts())
        .build()).toList();
    return  ResponseEntity.ok(categories);
  }

  @GetMapping("/find-id/{id}")
  public ResponseEntity<?> findById(@PathVariable String id){
    Optional<Category> categoryOptional = categoryService.findById(UUID.fromString(id));
    if(categoryOptional.isPresent()){
      Category category = categoryOptional.get();
      CategoryDTO categoryDTO = CategoryDTO.builder()
          .id(category.getId())
          .name(category.getName())
          .products(category.getProducts())
          .build();
      return ResponseEntity.ok(categoryDTO);
    }
    return ResponseEntity.notFound().build();
  }
  @PostMapping("/save")
  public ResponseEntity<?> save(@RequestBody CategoryDTO categoryDTO){
    if(categoryDTO.getName() != null){
      Category category = Category.builder()
          .name(categoryDTO.getName())
          .products(categoryDTO.getProducts())
          .build();
      categoryService.save(category);
      return ResponseEntity.ok("La categoria fue creada correctamente");
  }
    return ResponseEntity.badRequest().build();
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<?> update(@PathVariable String id, @RequestBody CategoryDTO categoryDTO){
    Category category = Category.builder()
        .name(categoryDTO.getName())
        .build();
    Optional<Category> categoryOptional = categoryService.update(UUID.fromString(id), category);
    if(categoryOptional.isPresent()){
      return ResponseEntity.ok("La categoria se actualizo correctamente");
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable String id){
    Optional<Category> categoryOptional = categoryService.delete(UUID.fromString(id));

    if(categoryOptional.isPresent()){
      return ResponseEntity.ok("La categoria fue eliminada correctamente");
    }
    return ResponseEntity.notFound().build();
  }
}
