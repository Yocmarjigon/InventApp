package com.application.inventApp.Controller.DTO.ValidationCustom;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Date;

public class DateValidation implements ConstraintValidator<DateValid, Date> {
  @Override
  public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
    if (date != null) return true;
    return false;
  }
}
