package com.family.petmemory.service;

import com.family.petmemory.entity.dto.*;
import com.family.petmemory.entity.member.Member;
import com.family.petmemory.entity.pet.Pet;
import com.family.petmemory.repository.member.MemberRepository;
import com.family.petmemory.repository.pet.DataJpaPetRepository;
import com.family.petmemory.repository.pet.DataJpaWeightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PetService {

    private final MemoryService memoryService;
    private final MemberRepository memberRepository;
    private final DataJpaPetRepository petRepository;
    private final DataJpaWeightRepository weightRepository;

    @Transactional
    public Long join(PetForm petForm) throws IOException {
        Member findMember = memberRepository.findById(petForm.getMemberId());
        Pet pet = new Pet(petForm.getName(), findMember, petForm.getBornTime());
        petRepository.save(pet);

        if (!petForm.getProfile().isEmpty()) {
            MemoryForm memoryForm = new MemoryForm(pet.getId());
            memoryForm.addFile(petForm.getProfile());
            pet.changeProfile(memoryService.join(memoryForm));
        }

        return pet.getId();
    }

    public PetDetailForm findPetDetail(Long petId) {
//        List<WeightForm> weightForms = weightRepository.findWeightForm(petId);
//        petDetailForm.addWeights(weightForms);
        PetDetailForm petDetailForm = petRepository.findPetDetail(petId);
        return petDetailForm;
    }

    public PetProfileForm findMyPet(Long petId) {
        return petRepository.findPetProfile(petId);
    }

    public List<PetProfileForm> findMyPets(Member member) {
        return petRepository.findPetProfiles(member);
    }

    public List<Pet> findPets() {
        return petRepository.findAll();
    }

    @Transactional
    public void leavePet(Long petId, LocalDate leaveTime) {
        petRepository.findById(petId).ifPresent(pet -> pet.leave(leaveTime));
    }
}
