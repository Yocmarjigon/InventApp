package com.application.inventApp.Controller.DTO.ValidationCustom;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = RolValidation.class)
public @interface RolValid {
  String message() default "El usuario necesita un rol valido";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
