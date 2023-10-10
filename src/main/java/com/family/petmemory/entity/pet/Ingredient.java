package com.family.petmemory.entity.pet;

import com.family.petmemory.entity.dto.IngredientDto;

import java.util.*;
import java.util.stream.Collectors;

public enum Ingredient {
    BEEF("BEEF","소고기", "Beef"),
    PORK("PORK", "돼지 고기", "Pork"),
    SALMON("SALMON", "연어", "Salmon"),
    POULTRY_MANURE("POULTRY_MANURE", "계분", "Poultry Manure"),
    CHICKEN_FAT("CHICKEN_FAT", "계유", "Chicken Fat"),
    CALCIUM("CALCIUM", "칼슘", "Calcium"),
    GRAIN("GRAIN", "곡류", "Grain"),
    REFINED_SALT("REFINED_SALT", "정제소금", "Refined Salt");

    private final String code;
    private final String kr;
    private final String eng;

    Ingredient(String code, String kr, String eng) {
        this.code = code;
        this.kr = kr;
        this.eng = eng;
    }

    public String getKr() {
        return kr;
    }

    public String getEng() {
        return eng;
    }

    public List<IngredientDto> getDto(Locale locale) {
        return Arrays.stream(Ingredient.values())
                .map(i -> new IngredientDto(i.code, checkLocale(i, locale)))
                .collect(Collectors.toList());
    }

    private String checkLocale(Ingredient i, Locale locale) {
        return locale == Locale.KOREA ? i.kr : i.eng;
    }
}
