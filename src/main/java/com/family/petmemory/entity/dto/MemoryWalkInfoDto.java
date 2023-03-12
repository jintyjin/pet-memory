package com.family.petmemory.entity.dto;

import com.family.petmemory.entity.memory.ImageSize;
import com.family.petmemory.entity.memory.UploadFile;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemoryWalkInfoDto {

    private Long memoryId;

    private String info;

    private LocalDateTime imageTime;

    private UploadFile uploadFile;

    private ImageSize imageSize;

    @QueryProjection
    public MemoryWalkInfoDto(Long memoryId, String info, LocalDateTime imageTime, UploadFile uploadFile, ImageSize imageSize) {
        this.memoryId = memoryId;
        this.info = info;
        this.imageTime = imageTime;
        this.uploadFile = uploadFile;
        this.imageSize = imageSize;
    }
}
