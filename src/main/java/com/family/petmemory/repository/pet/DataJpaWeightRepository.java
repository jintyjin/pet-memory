package com.family.petmemory.repository.pet;

import com.family.petmemory.entity.pet.Weight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface DataJpaWeightRepository extends JpaRepository<Weight, Long>, CustomWeightRepository {

    Optional<Weight> findByDate(LocalDate date);
}
