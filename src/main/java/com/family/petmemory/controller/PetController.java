package com.family.petmemory.controller;

import com.family.petmemory.entity.dto.PetForm;
import com.family.petmemory.entity.dto.PetProfileForm;
import com.family.petmemory.entity.member.Member;
import com.family.petmemory.entity.session.SessionConst;
import com.family.petmemory.service.PetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pets")
@Slf4j
public class PetController {

    private final PetService petService;

    @GetMapping("/new")
    public String createForm(Model model, @SessionAttribute(SessionConst.LOGIN_MEMBER) Member member) {
        model.addAttribute("petForm", new PetForm(member.getId()));
        return "/pets/createPetForm";
    }

    @PostMapping("/new")
    public String create(@Validated PetForm petForm, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return "/pets/createPetForm";
        }

        petService.join(petForm);

        return "redirect:/";
    }

    @GetMapping
    public String pets(Model model, @SessionAttribute(SessionConst.LOGIN_MEMBER) Member member) {
        List<PetProfileForm> petProfileFormList = petService.findMyPets(member);
        model.addAttribute("petProfileFormList", petProfileFormList);

        return "/pets/pets";
    }

    @GetMapping("{petId}")
    public String pet(Model model, @PathVariable Long petId) {
        PetProfileForm petProfileForm = petService.findMyPet(petId);
        model.addAttribute("petProfileForm", petProfileForm);

        return "/pets/pet";
    }
}
