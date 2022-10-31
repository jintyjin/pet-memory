package com.family.petmemory.validation.memoryForm;

import com.family.petmemory.entity.dto.MemoryForm;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public abstract class MemoryFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return MemoryForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
    }
}
