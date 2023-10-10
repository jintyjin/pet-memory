package com.family.petmemory.entity.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class FoodDto {

    private Long id;

    private String name;

    private String path;

    @QueryProjection
    public FoodDto(Long id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }
}
