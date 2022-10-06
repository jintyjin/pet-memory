package com.family.petmemory.controller;

import com.family.petmemory.entity.dto.PetForm;
import com.family.petmemory.entity.member.Member;
import com.family.petmemory.entity.pet.Pet;
import com.family.petmemory.entity.session.SessionConst;
import com.family.petmemory.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;

    @GetMapping("/new")
    public String createForm(Model model, @SessionAttribute(SessionConst.LOGIN_MEMBER) Member member) {
        model.addAttribute("petForm", new PetForm(member.getId()));
        return "/pets/createPetForm";
    }

    @PostMapping("/new")
    public String create(@Validated PetForm petForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/pets/createPetForm";
        }

        petService.join(petForm);

        return "redirect:/";
    }

    @GetMapping("/memory")
    public String memory(Model model, @SessionAttribute(SessionConst.LOGIN_MEMBER) Member member) {
        List<Pet> myPets = petService.findMyPets(member);
        model.addAttribute("myPets", myPets);
        return "/pets/memory";
    }
}
