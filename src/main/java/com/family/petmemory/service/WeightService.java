package com.family.petmemory.service;

import com.family.petmemory.entity.dto.WeightDto;
import com.family.petmemory.entity.dto.WeightForm;
import com.family.petmemory.entity.pet.Weight;
import com.family.petmemory.repository.pet.DataJpaPetRepository;
import com.family.petmemory.repository.pet.DataJpaWeightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WeightService {

    private final DataJpaPetRepository petRepository;
    private final DataJpaWeightRepository weightRepository;

    public List<WeightDto> findPetWeights(Long petId) {
        return weightRepository.findWeightForm(petId);
    }

    @Transactional
    public void savePetsWeights(WeightForm weightForm) {
        weightRepository.save(new Weight(weightForm.getWeight(), weightForm.getDate(), petRepository.findById(weightForm.getId()).get()));
    }
}
