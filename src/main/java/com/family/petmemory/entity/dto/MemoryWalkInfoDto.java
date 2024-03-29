package com.family.petmemory.entity.dto;

import com.family.petmemory.entity.memory.Gps;
import com.family.petmemory.infra.ImageSize;
import com.family.petmemory.infra.UploadFile;
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

    private Gps gps;

    @QueryProjection
    public MemoryWalkInfoDto(Long memoryId, String info, LocalDateTime imageTime, UploadFile uploadFile, ImageSize imageSize, Gps gps) {
        this.memoryId = memoryId;
        this.info = info;
        this.imageTime = imageTime;
        this.uploadFile = uploadFile;
        this.imageSize = imageSize;
        this.gps = gps;
    }
}
