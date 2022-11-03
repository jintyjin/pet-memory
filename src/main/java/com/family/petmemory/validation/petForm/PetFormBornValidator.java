package com.family.petmemory.validation.petForm;

import com.family.petmemory.entity.dto.PetForm;
import com.family.petmemory.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class PetFormBornValidator extends PetFormValidator {

    @Override
    public void validate(Object target, Errors errors) {
        PetForm petForm = (PetForm) target;

        if (petForm.getBornTime() != null && petForm.getBornTime().isAfter(LocalDate.now())) {
            errors.rejectValue("bornTime", "canNotBeforeNow");
        }
    }
}
