package com.family.petmemory.entity.dto;

import com.family.petmemory.entity.memory.MemoryType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemoryShowForm {

    private Long petId;

    private String petName;

    private Long memoryId;

    private String path;

    private MemoryType memoryType;
}
