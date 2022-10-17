package com.family.petmemory.controller;

import com.family.petmemory.entity.dto.MemoryForm;
import com.family.petmemory.entity.dto.PetIdAndName;
import com.family.petmemory.entity.dto.PetProfileForm;
import com.family.petmemory.entity.member.Member;
import com.family.petmemory.entity.session.SessionConst;
import com.family.petmemory.service.MemoryService;
import com.family.petmemory.service.PetService;
import com.family.petmemory.validation.MemoryForm.MemoryFormFilesValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/memories")
@Slf4j
public class MemoryController {

    private final PetService petService;
    private final MemoryService memoryService;
    private final MemoryFormFilesValidator memoryFormFilesValidator;

    @InitBinder
    public void init(WebDataBinder dataBinder) {
        dataBinder.addValidators(memoryFormFilesValidator);
    }

    @GetMapping("/memory")
    public String memory(Model model, @SessionAttribute(SessionConst.LOGIN_MEMBER) Member member) {
        List<PetProfileForm> petProfileForms = petService.findMyPets(member)
                .stream()
                .map(pet -> new PetProfileForm(pet.getId(), pet.getName(), pet.getProfile()))
                .collect(Collectors.toList());
        model.addAttribute("petProfileForms", petProfileForms);
        return "/memories/memory";
    }

    @GetMapping("/new")
    public String createForm(Model model, @SessionAttribute(SessionConst.LOGIN_MEMBER) Member member) {
        List<PetIdAndName> petIdAndNameList = petService.findMyPets(member)
                .stream()
                .map(pet -> new PetIdAndName(pet.getId(), pet.getName()))
                .collect(Collectors.toList());
        model.addAttribute("petIdAndNameList", petIdAndNameList);
        model.addAttribute("memoryForm", new MemoryForm());
        return "/memories/addMemoryForm";
    }

    @PostMapping("/new")
    public String create(Model model, @Validated MemoryForm memoryForm, BindingResult bindingResult, @SessionAttribute(SessionConst.LOGIN_MEMBER) Member member) {
        if (bindingResult.hasErrors()) {
            List<PetIdAndName> petIdAndNameList = petService.findMyPets(member)
                    .stream()
                    .map(pet -> new PetIdAndName(pet.getId(), pet.getName()))
                    .collect(Collectors.toList());
            model.addAttribute("petIdAndNameList", petIdAndNameList);
            return "/memories/addMemoryForm";
        }

        memoryService.join(memoryForm);

        return "/memories/memory";
    }
}
