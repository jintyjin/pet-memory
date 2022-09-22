package com.family.petmemory.repository.pet;

import com.family.petmemory.entity.Member;
import com.family.petmemory.entity.Pet;

import java.util.List;
import java.util.Optional;

public interface PetRepository {

    Long save(Pet pet);

    Pet findById(Long id);

    List<Pet> findByMember(Member member);

    List<Pet> findAll();
}
