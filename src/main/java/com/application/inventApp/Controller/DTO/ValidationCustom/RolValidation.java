package com.application.inventApp.Controller.DTO.ValidationCustom;

import com.application.inventApp.Entity.Enums.Rol;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Array;
import java.util.Arrays;

public class RolValidation implements ConstraintValidator<RolValid, String> {
  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    return Arrays.stream(Rol.values()).anyMatch(rol -> rol.name().equals(value));
  }
}
