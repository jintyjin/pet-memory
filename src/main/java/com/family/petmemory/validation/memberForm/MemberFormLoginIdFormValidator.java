package com.family.petmemory.validation.memberForm;

import com.family.petmemory.entity.dto.MemberForm;
import com.family.petmemory.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class MemberFormLoginIdFormValidator extends MemberFormValidator {

    private final MemberRepository memberRepository;

    @Override
    public void validate(Object target, Errors errors) {
        MemberForm memberForm = (MemberForm) target;

        if (memberRepository.findByLoginId(memberForm.getLoginId()).isPresent()) {
            errors.rejectValue("loginId", "existMember");
        }
    }
}
