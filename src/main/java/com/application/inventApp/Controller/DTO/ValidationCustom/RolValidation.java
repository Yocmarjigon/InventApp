package com.application.inventApp.Controller.DTO.ValidationCustom;

import com.application.inventApp.Enums.Rol;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class RolValidation implements ConstraintValidator<RolValid, String> {
  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    return Arrays.stream(Rol.values()).anyMatch(rol -> rol.name().equals(value));
  }
}
