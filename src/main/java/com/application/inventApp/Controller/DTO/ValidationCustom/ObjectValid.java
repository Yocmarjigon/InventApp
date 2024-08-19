package com.application.inventApp.Controller.DTO.ValidationCustom;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
@Constraint(validatedBy = ObjectValidation.class)
public @interface ObjectValid {
  String message() default "El objeto debe contar con al menos un id";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};

}
