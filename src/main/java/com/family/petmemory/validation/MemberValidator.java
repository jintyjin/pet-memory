package com.family.petmemory.validation;

import com.family.petmemory.entity.dto.MemberForm;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public abstract class MemberValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return MemberForm.class.isAssignableFrom(clazz);
        // member == clazz 타입이 같냐
        // member == 자식클래스 가능
    }

    @Override
    public void validate(Object target, Errors errors) {
    }
}
