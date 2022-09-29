package com.family.petmemory.validation.loginForm;

import com.family.petmemory.entity.dto.LoginForm;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public abstract class LoginFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return LoginForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
