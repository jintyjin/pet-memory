package com.family.petmemory.entity.dto;

import com.family.petmemory.entity.memory.MemoryType;
import com.family.petmemory.entity.pet.PetStatus;
import com.family.petmemory.entity.pet.TogetherTime;
import com.family.petmemory.entity.pet.Weight;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    public PetDetailForm(Long id, String name, String path, TogetherTime togetherTime, PetStatus petStatus, MemoryType type) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.bornTime = togetherTime.getBornTime();
        this.leaveTime = togetherTime.getLeaveTime();
        this.petStatus = petStatus;
        this.type = type;
    }

    public void addWeights(List<WeightForm> weights) {
        this.weights = weights;
    }
}
