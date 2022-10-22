package com.family.petmemory.entity.dto;

import com.family.petmemory.entity.memory.MemoryStatus;
import com.family.petmemory.entity.memory.MemoryType;
import lombok.Data;

@Data
public class MemorySearchCondition {
    
    private Long petId;
    private Long memoryId;
    private MemoryStatus memoryStatus;
    private MemoryType memoryType;

    public MemorySearchCondition(Long petId, Long memoryId, MemoryStatus memoryStatus, MemoryType memoryType) {
        this.petId = petId;
        this.memoryId = memoryId;
        this.memoryStatus = memoryStatus;
        this.memoryType = memoryType;
    }
}
