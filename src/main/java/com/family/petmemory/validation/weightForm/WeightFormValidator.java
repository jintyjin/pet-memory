package com.family.petmemory.validation.weightForm;

import com.family.petmemory.entity.dto.WeightForm;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public abstract class WeightFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return WeightForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
    }
}
