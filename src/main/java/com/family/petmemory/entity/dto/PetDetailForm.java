package com.family.petmemory.entity.dto;

import com.family.petmemory.entity.memory.MemoryType;
import com.family.petmemory.entity.pet.PetStatus;
import com.family.petmemory.entity.pet.TogetherTime;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PetDetailForm {

    private Long id;

    private String name;

    private String path;

    private TogetherTime togetherTime;

    private PetStatus petStatus;

    private MemoryType type;

    @QueryProjection
    public PetDetailForm(Long id, String name, String path, TogetherTime togetherTime, PetStatus petStatus, MemoryType type) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.togetherTime = togetherTime;
        this.petStatus = petStatus;
        this.type = type;
    }
}
