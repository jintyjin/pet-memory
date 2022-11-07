package com.family.petmemory.repository.pet;

import com.family.petmemory.entity.dto.WeightForm;

import java.util.List;

public interface CustomWeightRepository {

    List<WeightForm> findWeightForm(Long petId);
}
