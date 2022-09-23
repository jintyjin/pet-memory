package com.family.petmemory.controller;

import com.family.petmemory.entity.dto.MemberForm;
import com.family.petmemory.entity.member.Member;
import com.family.petmemory.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "/members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Validated MemberForm memberForm, BindingResult result) {
        if (result.hasErrors()) {
            return "/members/createMemberForm";
        }

        Member member = new Member(memberForm.getLoginId(), memberForm.getName(), memberForm.getPassword());
        memberService.join(member);

        return "redirect:/";
    }
}
