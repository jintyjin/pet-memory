package com.family.petmemory.controller;

import com.family.petmemory.entity.dto.PetProfileForm;
import com.family.petmemory.entity.member.Member;
import com.family.petmemory.entity.session.SessionConst;
import com.family.petmemory.service.PetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/memories")
@Slf4j
public class MemoryController {

    private final PetService petService;

    @GetMapping("/memory")
    public String memory(Model model, @SessionAttribute(SessionConst.LOGIN_MEMBER) Member member) {
        List<PetProfileForm> petProfileForms = petService.findMyPets(member)
                .stream()
                .map(pet -> new PetProfileForm(pet.getName(), pet.getProfile()))
                .collect(Collectors.toList());
        model.addAttribute("petProfileForms", petProfileForms);
        return "/memories/memory";
    }

}
