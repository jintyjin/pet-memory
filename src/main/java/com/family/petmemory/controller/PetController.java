package com.family.petmemory.controller;

import com.family.petmemory.entity.dto.PetDetailForm;
import com.family.petmemory.entity.dto.PetForm;
import com.family.petmemory.entity.dto.PetProfileForm;
import com.family.petmemory.entity.member.Member;
import com.family.petmemory.entity.session.SessionConst;
import com.family.petmemory.service.PetService;
import com.family.petmemory.validation.petDetailForm.PetDetailFormLeaveValidator;
import com.family.petmemory.validation.petForm.PetFormBornValidator;
import com.family.petmemory.validation.petForm.PetFormNameBornValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pets")
@Slf4j
public class PetController {

    private final PetService petService;
    private final PetFormNameBornValidator petFormNameBornValidator;
    private final PetFormBornValidator petFormBornValidator;
    private final PetDetailFormLeaveValidator petDetailFormLeaveValidator;

    @InitBinder("petForm")
    public void initPetForm(WebDataBinder dataBinder) {
        dataBinder.addValidators(petFormNameBornValidator);
        dataBinder.addValidators(petFormBornValidator);
    }

    @InitBinder("petDetailForm")
    public void initPetDetailForm(WebDataBinder dataBinder) {
        dataBinder.addValidators(petDetailFormLeaveValidator);
    }

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

    @GetMapping("/{petId}")
    public String pet(Model model, @PathVariable Long petId) {
        PetDetailForm petDetailForm = petService.findPetDetail(petId);
        model.addAttribute("petDetailForm", petDetailForm);

        return "/pets/pet";
    }

    @PostMapping("/{petId}")
    public String pet(@Validated PetDetailForm petDetailForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/pets/pet";
        }

        petService.leavePet(petDetailForm.getId(), petDetailForm.getLeaveTime());

        return "/pets/pets";
    }
}
