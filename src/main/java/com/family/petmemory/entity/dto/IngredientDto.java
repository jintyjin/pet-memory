package com.family.petmemory.entity.dto;

import lombok.Data;

@Data
public class IngredientDto {

    private String code;
    private String ingredient;

    protected IngredientDto() {
    }

    public IngredientDto(String code, String ingredient) {
        this.code = code;
        this.ingredient = ingredient;
    }
}
