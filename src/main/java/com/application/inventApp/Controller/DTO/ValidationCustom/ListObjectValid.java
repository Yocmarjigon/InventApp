package com.application.inventApp.Controller.DTO.ValidationCustom;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ListObjectValidation.class)
public @interface ListObjectValid {
  String message() default "La lista no debe estar vacia ";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
