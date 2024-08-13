package com.application.inventApp.Controller.DTO.ValidationCustom;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = PasswordValidation.class)
public @interface PasswordValid {
  String message() default "La contraseña debe contener una longitud mayor o igual a 6 y como minimo un caracter en mayusculas";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}

