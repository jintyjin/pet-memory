package com.family.petmemory.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FoodThumbnailForm {

    private Long id;

    private String name;

    private String path;
}
