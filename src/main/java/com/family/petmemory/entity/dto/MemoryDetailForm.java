package com.family.petmemory.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemoryDetailForm {

    private Long memoryId;

    private String info;

    private LocalDateTime imageTime;

    private String imageName;

    private int width;  // 이미지 너비

    private int height; // 이미지 크기

    private Double Latitude;    // 위도

    private Double Longitude;   // 경도
}
