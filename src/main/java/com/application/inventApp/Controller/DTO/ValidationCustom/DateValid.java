package com.application.inventApp.Controller.DTO.ValidationCustom;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidation.class)
public @interface DateValid {
  String message() default "La fecha no puede estar vacia";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
