package com.family.petmemory.entity.dto;

import com.family.petmemory.entity.memory.MemoryType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PetProfileForm {

    private Long id;

    private String name;

    private String path;

    private MemoryType type;

    @QueryProjection
    public PetProfileForm(Long id, String name, String path, MemoryType type) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.type = type;
    }
}
