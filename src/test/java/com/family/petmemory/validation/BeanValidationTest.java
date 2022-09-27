package com.family.petmemory.validation;

import com.family.petmemory.entity.dto.MemberForm;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class BeanValidationTest {

    @Test
    void beanValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        MemberForm memberForm = new MemberForm();
        memberForm.setLoginId(" ");
        memberForm.setPassword("");
        memberForm.setName("member");
        memberForm.setEmail("11");
        memberForm.setBirth(null);

        Set<ConstraintViolation<MemberForm>> validations = validator.validate(memberForm);
        for (ConstraintViolation<MemberForm> validation : validations) {
            System.out.println("validation = " + validation);
            System.out.println("validation.getMessage() = " + validation.getMessage());
        }
    }
}
