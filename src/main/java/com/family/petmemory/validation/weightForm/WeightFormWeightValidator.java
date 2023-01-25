package com.family.petmemory.validation.weightForm;

import com.family.petmemory.entity.dto.WeightForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class WeightFormWeightValidator extends WeightFormValidator {

    @Override
    public void validate(Object target, Errors errors) {
        WeightForm weightForm = (WeightForm) target;

        if (weightForm.getWeight() == null) {
            errors.rejectValue("weight", "NotNull");
        }
    }
}
