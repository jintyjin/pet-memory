package com.family.petmemory.controller;

import com.family.petmemory.entity.dto.MemberForm;
import com.family.petmemory.entity.member.Member;
import com.family.petmemory.service.MemberService;
import com.family.petmemory.validation.memberForm.MemberFormLoginIdFormValidator;
import com.family.petmemory.validation.memberForm.MemberFormNameFormValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final MemberFormLoginIdFormValidator memberFormLoginIdValidator;
    private final MemberFormNameFormValidator memberFormNameValidator;

    @InitBinder
    public void init(WebDataBinder dataBinder) {
        dataBinder.addValidators(memberFormLoginIdValidator);
        dataBinder.addValidators(memberFormNameValidator);
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "/members/createMemberForm";
    }

    @PostMapping("/new")
    public String create(@Validated MemberForm memberForm, BindingResult result) {
        if (result.hasErrors()) {
            return "/members/createMemberForm";
        }

        Member member = new Member(memberForm.getLoginId(), memberForm.getName(), memberForm.getPassword(), memberForm.getEmail(), memberForm.getBirth());
        memberService.join(member);

        return "redirect:/";
    }
}
