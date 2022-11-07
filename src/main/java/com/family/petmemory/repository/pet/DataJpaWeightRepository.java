package com.family.petmemory.repository.pet;

import com.family.petmemory.entity.pet.Weight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataJpaWeightRepository extends JpaRepository<Weight, Long>, CustomWeightRepository {

}
