package com.family.petmemory.validation.weightForm;

import com.family.petmemory.entity.dto.WeightForm;
import com.family.petmemory.entity.pet.Pet;
import com.family.petmemory.repository.pet.DataJpaPetRepository;
import com.family.petmemory.repository.pet.DataJpaWeightRepository;
import com.family.petmemory.repository.pet.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class WeightFormDateValidator extends WeightFormValidator {

    private final DataJpaPetRepository petRepository;
    private final DataJpaWeightRepository weightRepository;

    @Override
    public void validate(Object target, Errors errors) {
        WeightForm weightForm = (WeightForm) target;
        Pet pet = petRepository.findById(weightForm.getId()).get();

        if (pet.getTogetherTime().getBornTime() != null && weightForm.getDate().isBefore(pet.getTogetherTime().getBornTime())) {
            errors.rejectValue("date", "canNotBeforeBornTime");
        }

        if (weightForm.getDate().isAfter(LocalDate.now())) {
            errors.rejectValue("date", "canNotAfterNow");
        }

        weightRepository.findByDate(weightForm.getDate())
                .ifPresent(weight ->
                        errors.rejectValue("date", "existDate")
                );

    }
}
