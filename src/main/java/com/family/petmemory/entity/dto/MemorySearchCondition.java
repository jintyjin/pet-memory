package com.family.petmemory.entity.dto;

import lombok.Data;

@Data
public class MemorySearchCondition {
    
    private Long petId;
    private Long memoryId;

    public MemorySearchCondition(Long petId, Long memoryId) {
        this.petId = petId;
        this.memoryId = memoryId;
    }
}
