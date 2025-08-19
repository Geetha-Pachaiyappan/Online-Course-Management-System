package com.example.Online.Course.Management.System.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Constraint(validatedBy = EnrollmentStatusValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEnrollmentStatus {
    String message() default "Invalid Enrollment Status";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
