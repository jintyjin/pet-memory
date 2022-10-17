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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

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
        List<PetProfileForm> petProfileFormList = petService.findMyPets(member)
                .stream()
                .map(pet -> new PetProfileForm(pet.getId(), pet.getName(), pet.getProfile()))
                .collect(Collectors.toList());
        model.addAttribute("petProfileFormList", petProfileFormList);

        return "/pets/pets";
    }
}
