package com.example.Online.Course.Management.System.validations;

import com.example.Online.Course.Management.System.enums.Roles;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.Arrays;

public class RoleValidator implements ConstraintValidator<ValidRole,String>
{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null)
            return false;
        return Arrays.stream(Roles.values())
                .anyMatch(roles -> roles.name().equalsIgnoreCase(value));
    }
}
