package com.family.petmemory.validation.petForm;

import com.family.petmemory.entity.dto.PetForm;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public abstract class PetFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PetForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
    }
}
