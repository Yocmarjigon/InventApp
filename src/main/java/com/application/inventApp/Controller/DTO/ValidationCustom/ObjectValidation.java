package com.application.inventApp.Controller.DTO.ValidationCustom;

import com.application.inventApp.Controller.DTO.BaseEntityDTO;
import com.application.inventApp.Controller.DTO.ProductsDTOs.ProductDTO;
import com.application.inventApp.Entity.BaseEntity;
import com.application.inventApp.Entity.Product;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ObjectValidation implements ConstraintValidator<ObjectValid, BaseEntity> {
  @Override
  public boolean isValid(BaseEntity obj, ConstraintValidatorContext constraintValidatorContext) {

    if (obj != null && obj.getId() != null){
      return true;
    }
    return false;
  }
}
