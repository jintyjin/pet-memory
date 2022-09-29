package com.family.petmemory.validation;

import com.family.petmemory.entity.dto.MemberForm;
import com.family.petmemory.entity.member.Member;
import com.family.petmemory.repository.member.MemberRepository;
import com.family.petmemory.validation.memberForm.MemberFormLoginIdFormValidator;
import com.family.petmemory.validation.memberForm.MemberFormNameFormValidator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;

@SpringBootTest
public class CustomValidationTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberFormLoginIdFormValidator memberFormLoginIdValidator;

    @Autowired
    MemberFormNameFormValidator memberFormNameValidator;

    @Test
    @Transactional
    @Rollback(value = false)
    void customValidation() {
        //given
        Member member = new Member("member123", "홍길동", "member!23456", "j@abc.com", LocalDate.now());
        memberRepository.save(member);

        MemberForm memberForm = new MemberForm();
        memberForm.setLoginId(member.getLoginId());
        memberForm.setPassword(member.getPassword());
        memberForm.setName(member.getName());
        memberForm.setEmail(member.getEmail());
        memberForm.setBirth(member.getBirth());

        //when
        BindingResult bindingResult = Mockito.mock(BindingResult.class);

        memberFormLoginIdValidator.validate(memberForm, bindingResult);
        memberFormNameValidator.validate(memberForm, bindingResult);

        System.out.println(bindingResult);

    }
}
