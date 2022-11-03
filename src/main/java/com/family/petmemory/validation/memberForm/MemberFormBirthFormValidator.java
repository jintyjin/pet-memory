package com.family.petmemory.validation.memberForm;

import com.family.petmemory.entity.dto.MemberForm;
import com.family.petmemory.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.time.LocalDate;

@RequiredArgsConstructor
@Component
public class MemberFormBirthFormValidator extends MemberFormValidator {

    @Override
    public void validate(Object target, Errors errors) {
        MemberForm memberForm = (MemberForm) target;

        if (memberForm.getBirth() != null && memberForm.getBirth().isAfter(LocalDate.now())) {
            errors.rejectValue("birth", "canNotBeforeNow");
        }
    }
}
