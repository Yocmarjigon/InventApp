package com.application.inventApp.Controller.DTO.ValidationCustom;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PasswordValidation implements ConstraintValidator<PasswordValid, String> {
  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

    return value.length() >= 6 && Pattern.compile(".*[A-Z].*").matcher(value).matches();
  }
}
