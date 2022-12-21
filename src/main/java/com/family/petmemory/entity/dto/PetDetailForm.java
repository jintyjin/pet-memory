package com.family.petmemory.entity.dto;

import com.family.petmemory.entity.memory.MemoryType;
import com.family.petmemory.entity.pet.Pet;
import com.family.petmemory.entity.pet.PetStatus;
import com.family.petmemory.entity.pet.TogetherTime;
import com.family.petmemory.entity.pet.Weight;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PetDetailForm {

    private Long id;

    private String name;

    private String path;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate bornTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate leaveTime;

    private PetStatus petStatus;

    private List<WeightForm> weights = new ArrayList<>();

    private MemoryType type;

    @QueryProjection
    public PetDetailForm(Pet pet, String path, MemoryType type) {
        this.id = pet.getId();
        this.name = pet.getName();
        this.path = path;
        this.bornTime = pet.getTogetherTime().getBornTime();
        this.leaveTime = pet.getTogetherTime().getLeaveTime();
        this.petStatus = pet.getPetStatus();
        this.weights = pet.getWeights().stream()
                .sorted(Comparator.comparing(Weight::getDate))
                .map(weight -> new WeightForm(weight.getId(), weight.getDate(), weight.getWeight()))
                .collect(Collectors.toList());
        this.type = type;
    }

    public void addWeights(List<WeightForm> weights) {
        this.weights = weights;
    }
}
