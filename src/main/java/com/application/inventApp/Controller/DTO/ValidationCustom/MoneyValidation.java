package com.application.inventApp.Controller.DTO.ValidationCustom;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class MoneyValidation implements ConstraintValidator<MoneyValid, BigDecimal> {

  @Override
  public boolean isValid(BigDecimal bigDecimal, ConstraintValidatorContext constraintValidatorContext) {
    Pattern pattern = Pattern.compile("^(\\\\d{1,3}(\\\\.\\\\d{3})*|\\\\d{1,3})(,\\\\d{1,2})?$");

    if(pattern.equals(bigDecimal)){
      System.out.println(pattern.equals(bigDecimal));
      return true;
    }
    return false;
  }
}
