package com.family.petmemory.repository.pet;

import com.family.petmemory.entity.Pet;

import java.util.List;

public interface PetRepository {

    Long save(Pet pet);

    Pet findOne(Long id);

    List<Pet> findAll();
}
