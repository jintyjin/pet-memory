package com.family.petmemory.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemoryShowForm {

    private Long petId;

    private String petName;

    private Long memoryId;

    private String path;


}
