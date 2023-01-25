package com.family.petmemory.controller;

import com.family.petmemory.entity.dto.PetProfileForm;
import com.family.petmemory.entity.dto.WeightDto;
import com.family.petmemory.entity.dto.WeightForm;
import com.family.petmemory.entity.pet.Pet;
import com.family.petmemory.repository.pet.DataJpaPetRepository;
import com.family.petmemory.repository.pet.DataJpaWeightRepository;
import com.family.petmemory.service.PetService;
import com.family.petmemory.service.WeightService;
import com.family.petmemory.validation.weightForm.WeightFormDateValidator;
import com.family.petmemory.validation.weightForm.WeightFormWeightValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/weights")
@Slf4j
public class WeightController {

    private final DataJpaPetRepository petRepository;
    private final DataJpaWeightRepository weightRepository;
    private final WeightService weightService;
    private final WeightFormDateValidator weightFormDateValidator;
    private final WeightFormWeightValidator weightFormWeightValidator;

    @InitBinder("weightForm")
    public void initPetForm(WebDataBinder dataBinder) {
        dataBinder.addValidators(weightFormDateValidator);
        dataBinder.addValidators(weightFormWeightValidator);
    }

    @GetMapping("/{petId}")
    public String weights(Model model, @PathVariable Long petId) {
        Optional<Pet> optionalPet = petRepository.findById(petId);
        if (!optionalPet.isPresent()) {
            return "/redirect:/";
        }

        Pet pet = optionalPet.get();

        List<WeightDto> weightFormList = weightService.findPetWeights(petId);

        model.addAttribute("weightForm", new WeightForm(petId, pet.getName(), LocalDate.now(), null, weightFormList));

        return "/pets/weights";
    }

    @PostMapping("/{petId}")
    public String weights(@PathVariable Long petId, @Validated WeightForm weightForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/pets/weights";
        }

        weightService.savePetsWeights(weightForm);

        return "redirect:/weights/" + petId;
    }

    @GetMapping("/delete")
    public String deleteWeight(@RequestParam Long id, @RequestParam Long petId) {
        if (weightRepository.existsById(id)) {
            weightRepository.deleteById(id);
        }

        return "redirect:/weights/" + petId;
    }
}
