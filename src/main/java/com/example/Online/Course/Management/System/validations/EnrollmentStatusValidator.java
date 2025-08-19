package com.example.Online.Course.Management.System.validations;

import com.example.Online.Course.Management.System.enums.EnrollmentStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class EnrollmentStatusValidator implements ConstraintValidator<ValidEnrollmentStatus,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null || value.isEmpty())
          return false;
        return Arrays.asList(EnrollmentStatus.values()).stream()
                .anyMatch(status -> status.name().equalsIgnoreCase(value));
    }
}
