package com.family.petmemory.entity.dto;

import com.family.petmemory.entity.memory.MemoryStatus;
import lombok.Data;

@Data
public class MemorySearchCondition {
    
    private Long petId;
    private Long memoryId;
    private MemoryStatus memoryStatus;

    public MemorySearchCondition(Long petId, Long memoryId, MemoryStatus memoryStatus) {
        this.petId = petId;
        this.memoryId = memoryId;
        this.memoryStatus = memoryStatus;
    }
}
