package com.application.inventApp.Controller.DTO.ValidationCustom;

import java.lang.annotation.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MoneyValidation.class)

public @interface MoneyValid {
  String message() default "El objeto debe contar con al menos un id";
  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
