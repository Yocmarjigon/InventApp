package com.application.inventApp.Controller.DTO.ValidationCustom;

import com.application.inventApp.Controller.DTO.SaleDTOs.ProductsSoldDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class NumberSaleValidation implements ConstraintValidator<NumberSaleValid, List<ProductsSoldDTO>> {
  @Override
  public boolean isValid(List<ProductsSoldDTO> productsSoldDTOS, ConstraintValidatorContext constraintValidatorContext) {
    if(productsSoldDTOS.stream().anyMatch(productsSoldDTO -> productsSoldDTO.getNumberSale() != 0 && productsSoldDTOS != null)) return true;
    return false;
  }
}
