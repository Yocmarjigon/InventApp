package com.application.inventApp.Controller;

import com.application.inventApp.Controller.DTO.CategoryDTOs.CategoryDTO;
import com.application.inventApp.Controller.Response.ResponseOK;
import com.application.inventApp.Entity.Category;
import com.application.inventApp.Services.Impl.CategoryService;
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
@RequestMapping("/category")
public class CategoryController {
  @Autowired
  private CategoryService categoryService;
  private ModelMapper modelMapper = new ModelMapper();

  @GetMapping("/find-all")
  public ResponseEntity<?> findAll()  {
    try{
      List<CategoryDTO> categories = categoryService.findAll().stream().map(category -> modelMapper.map(category, CategoryDTO.class)).toList();
      return  ResponseEntity.ok(categories);
    }catch (Exception e){
      throw e;
    }

  }

  @GetMapping("/find-id/{id}")
  public ResponseEntity<?> findById(@PathVariable String id){
    try {
      Optional<Category> categoryOptional = categoryService.findById(UUID.fromString(id));
      if(categoryOptional.isPresent()){
        Category category = categoryOptional.get();
        CategoryDTO categoryDTO =modelMapper.map(category, CategoryDTO.class);
        return ResponseEntity.ok(categoryDTO);
      }
      return ResponseEntity.notFound().build();
    }catch (Exception e){
      throw e;
    }

  }

  @PostMapping("/save")
  public ResponseEntity<?> save(@Valid @RequestBody CategoryDTO categoryDTO, BindingResult bindingResult){
    try{
      if(bindingResult.hasFieldErrors()){
        return new ResponseEntity<>(new ResponseOK(bindingResult.getFieldError().getDefaultMessage()), HttpStatus.BAD_REQUEST);
      }

      Category category = modelMapper.map(categoryDTO, Category.class);
      categoryService.save(category);
      return ResponseEntity.ok(new ResponseOK("La categoria fue creada correctamente"));

    }catch (Exception e){
      throw e;
    }
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<?> update(@PathVariable String id, @RequestBody CategoryDTO categoryDTO){
    try{
      Category category = modelMapper.map(categoryDTO, Category.class);
      Optional<Category> categoryOptional = categoryService.update(UUID.fromString(id), category);
      if(categoryOptional.isPresent()){
        return ResponseEntity.ok(new ResponseOK("La categoria se actualizo correctamente"));
      }
      return ResponseEntity.notFound().build();
    }catch (Exception e ){
      throw e;
    }

  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable String id){
    try{
      Optional<Category> categoryOptional = categoryService.delete(UUID.fromString(id));

      if(categoryOptional.isPresent()){
        return ResponseEntity.ok(new ResponseOK("La categoria fue eliminada correctamente"));
      }
      return ResponseEntity.notFound().build();
    }catch (Exception e){
      throw e;
    }

  }
}
