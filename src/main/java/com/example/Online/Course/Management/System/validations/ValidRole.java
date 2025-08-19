package com.example.Online.Course.Management.System.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Constraint(validatedBy = RoleValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRole {
    String message() default "Role Invalid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
