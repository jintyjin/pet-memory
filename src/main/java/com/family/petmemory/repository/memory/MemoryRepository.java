package com.family.petmemory.repository.memory;

import com.family.petmemory.entity.memory.Memory;
import com.family.petmemory.entity.pet.Pet;

import java.util.List;

public interface MemoryRepository {

    Long save(Memory memory);

    Memory findById(Long id);

    List<Memory> findByPet(Pet pet);

    List<Memory> findAll();
}
