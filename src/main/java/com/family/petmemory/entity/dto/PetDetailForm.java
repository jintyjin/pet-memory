package com.family.petmemory.entity.dto;

import com.family.petmemory.entity.memory.MemoryType;
import com.family.petmemory.entity.pet.Pet;
import com.family.petmemory.entity.pet.PetStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

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

    private MemoryType type;

    @QueryProjection
    public PetDetailForm(Pet pet, String path, MemoryType type) {
        this.id = pet.getId();
        this.name = pet.getName();
        this.path = path;
        this.bornTime = pet.getTogetherTime().getBornTime();
        this.leaveTime = pet.getTogetherTime().getLeaveTime();
        this.petStatus = pet.getPetStatus();
        this.type = type;
    }
}
