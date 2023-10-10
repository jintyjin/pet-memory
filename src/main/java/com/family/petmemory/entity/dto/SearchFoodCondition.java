package com.family.petmemory.entity.dto;

import com.family.petmemory.entity.pet.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SearchFoodCondition {

    // 검색어
    private String search;

    // 선택 성분
    private List<Ingredient> ingredients;

    protected SearchFoodCondition() {
    }
}
