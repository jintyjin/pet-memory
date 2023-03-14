package com.family.petmemory.controller;

import com.family.petmemory.entity.dto.*;
import com.family.petmemory.entity.member.Member;
import com.family.petmemory.entity.memory.MemoryStatus;
import com.family.petmemory.entity.session.SessionConst;
import com.family.petmemory.service.MemoryService;
import com.family.petmemory.service.PetService;
import com.family.petmemory.validation.memoryForm.MemoryFormFilesValidator;
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

    @InitBinder("memoryForm")
    public void init(WebDataBinder dataBinder) {
        dataBinder.addValidators(memoryFormFilesValidator);
    }

    @GetMapping("/memory")
    public String memory(Model model, @SessionAttribute(SessionConst.LOGIN_MEMBER) Member member) {
        List<PetProfileForm> petProfileForms = petService.findMyPets(member);
        model.addAttribute("petProfileForms", petProfileForms);
        return "/memories/memory";
    }

    @GetMapping("/new")
    public String createForm(Model model, @SessionAttribute(SessionConst.LOGIN_MEMBER) Member member) {
        List<PetIdAndName> petIdAndNameList = petService.findMyPets(member)
                .stream()
                .map(petProfileForm -> new PetIdAndName(petProfileForm.getId(), petProfileForm.getName()))
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

    @GetMapping("/memory/{petId}")
    public String memories(Model model, @PathVariable Long petId) {
        List<MemoryShowForm> memories = memoryService.showPetMemories(petId, MemoryStatus.NORMAL);

        model.addAttribute("memories", memories);

        return "/memories/memories";
    }

    @GetMapping("/memory/detail/{memoryId}")
    public String detail(Model model, @PathVariable Long memoryId) {
        MemoryDetailForm memoryDetailForm = memoryService.showMemoryDetail(memoryId);

        model.addAttribute("memoryDetailForm", memoryDetailForm);

        return "/memories/detail";
    }

    @PostMapping("/memory/detail/update/info")
    @ResponseBody
    public String updateDetailInfo(@RequestBody MemoryDetailUpdateInfoDto saveInfoDto) {
        String response = null;

        try {
            response = memoryService.updateMemoryInfo(saveInfoDto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @GetMapping("/walk/{petId}")
    public String walk(@PathVariable Long petId, Model model) {
        List<MemoryWalkForm> memoryWalkForms = memoryService.showMemoryWalk(petId);

        model.addAttribute("memoryWalkForms", memoryWalkForms);

        return "/memories/walk";
    }

    @GetMapping("/walk/info/{memoryId}")
    @ResponseBody
    public MemoryWalkInfoDto showWalkInfo(@PathVariable Long memoryId) {
        return memoryService.showMemoryWalkInfo(memoryId);
    }
}
