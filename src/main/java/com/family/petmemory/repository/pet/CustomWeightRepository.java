package com.family.petmemory.repository.pet;

import com.family.petmemory.entity.dto.WeightDto;

import java.util.List;

public interface CustomWeightRepository {

    List<WeightDto> findWeightForm(Long petId);
}
