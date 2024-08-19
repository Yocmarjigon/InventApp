package com.application.inventApp.Controller.DTO.ValidationCustom;


import com.application.inventApp.Entity.Product;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class ListObjectValidation implements ConstraintValidator<ListObjectValid, List<Product>> {

  @Override
  public boolean isValid(List<Product> products, ConstraintValidatorContext constraintValidatorContext) {
    if (products != null && !products.isEmpty()) return true;
    return false;
  }
}
