package com.family.petmemory.entity.dto;

import com.family.petmemory.entity.memory.Gps;
import com.family.petmemory.infra.ImageSize;
import com.family.petmemory.entity.memory.MemoryStatus;
import com.family.petmemory.entity.memory.MemoryType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemoryWalkForm {

    private Long memoryId;

    private String saveFileName;

    private LocalDateTime imageTime;

    private ImageSize imageSize;

    private Gps gps;

    private MemoryStatus memoryStatus;

    private MemoryType memoryType;

    @QueryProjection
    public MemoryWalkForm(Long memoryId, String saveFileName, LocalDateTime imageTime, ImageSize imageSize, Gps gps, MemoryStatus memoryStatus, MemoryType memoryType) {
        this.memoryId = memoryId;
        this.saveFileName = saveFileName;
        this.imageTime = imageTime;
        this.imageSize = imageSize;
        this.gps = gps;
        this.memoryStatus = memoryStatus;
        this.memoryType = memoryType;
    }
}
