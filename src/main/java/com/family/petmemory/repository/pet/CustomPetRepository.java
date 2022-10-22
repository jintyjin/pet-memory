package com.family.petmemory.repository.pet;

import com.family.petmemory.entity.dto.PetProfileForm;
import com.family.petmemory.entity.member.Member;

import java.util.List;

public interface CustomPetRepository {

    PetProfileForm findPetProfile(Long petId);
    List<PetProfileForm> findPetProfiles(Member member);
}
