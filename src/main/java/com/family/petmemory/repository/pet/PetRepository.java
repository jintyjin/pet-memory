package com.family.petmemory.repository.pet;

import com.family.petmemory.entity.member.Member;
import com.family.petmemory.entity.pet.Pet;
import com.family.petmemory.entity.pet.PetStatus;

import java.util.List;

public interface PetRepository {

    Long save(Pet pet);

    Pet findById(Long id);

    List<Pet> findByMemberAndPetStatus(Member member, PetStatus petStatus);

    List<Pet> findByMember(Member member);

    List<Pet> findAll();
}
