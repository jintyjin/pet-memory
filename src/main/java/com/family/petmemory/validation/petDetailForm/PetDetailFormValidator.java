package com.family.petmemory.validation.petDetailForm;

import com.family.petmemory.entity.dto.PetDetailForm;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public abstract class PetDetailFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PetDetailForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
    }
}
