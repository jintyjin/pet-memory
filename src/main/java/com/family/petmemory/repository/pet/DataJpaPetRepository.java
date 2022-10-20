package com.family.petmemory.repository.pet;

import com.family.petmemory.entity.pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataJpaPetRepository extends JpaRepository<Pet, Long>, CustomPetRepository {
}
