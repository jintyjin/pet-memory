package com.family.petmemory.validation.loginForm;

import com.family.petmemory.entity.dto.LoginForm;
import com.family.petmemory.entity.member.Member;
import com.family.petmemory.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class LoginFormLoginValidator extends LoginFormValidator {

    private final MemberRepository memberRepository;

    @Override
    public void validate(Object target, Errors errors) {
        LoginForm loginForm = (LoginForm) target;

        Optional<Member> byLoginId = memberRepository.findByLoginId(loginForm.getLoginId());

        if (byLoginId.isPresent()) {
            if (!byLoginId.get().getPassword().equals(loginForm.getPassword())) {
                errors.rejectValue("password", "wrongPassword");
            }
        } else {
            errors.rejectValue("loginId", "notExistMember");
        }
    }
}
