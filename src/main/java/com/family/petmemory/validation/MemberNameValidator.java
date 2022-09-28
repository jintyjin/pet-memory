package com.family.petmemory.validation;

import com.family.petmemory.entity.dto.MemberForm;
import com.family.petmemory.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class MemberNameValidator extends MemberValidator {

    private final MemberRepository memberRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return super.supports(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberForm memberForm = (MemberForm) target;

        if (memberRepository.findByName(memberForm.getName()).stream().findAny().isPresent()) {
            errors.rejectValue("name", "existMember");
        }
    }
}
