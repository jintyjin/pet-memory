package com.family.petmemory.validation.PetForm;

import com.family.petmemory.entity.dto.PetForm;
import com.family.petmemory.repository.member.MemberRepository;
import com.family.petmemory.repository.pet.DataJpaPetRepository;
import com.family.petmemory.repository.pet.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
@RequiredArgsConstructor
public class PetFormNameBornValidator extends PetFormValidator {

    private final MemberRepository memberRepository;

    @Override
    public void validate(Object target, Errors errors) {
        PetForm petForm = (PetForm) target;

        memberRepository.findById(petForm.getMemberId())
                .getPets()
                .stream()
                .filter(pet -> pet.getName().equals(petForm.getName()) && pet.getTogetherTime().getBornTime().isEqual(petForm.getBornTime()))
                .findAny()
                .ifPresent(pet -> errors.rejectValue("name", "existPet"));
    }
}
