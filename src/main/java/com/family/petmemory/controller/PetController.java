package com.family.petmemory.controller;

import com.family.petmemory.entity.dto.MemoryForm;
import com.family.petmemory.entity.dto.PetForm;
import com.family.petmemory.entity.member.Member;
import com.family.petmemory.entity.session.SessionConst;
import com.family.petmemory.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;

    @Value("${file.dir}")
    private String fileDir;

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
        List<MemoryForm> memoryForms = petService.findMyPets(member)
                .stream()
                .map(pet -> new MemoryForm(pet.getName(), pet.getThumbnail()))
                .collect(Collectors.toList());
        model.addAttribute("memoryForms", memoryForms);
        return "/pets/memory";
    }
}
