package com.family.petmemory.entity.dto;

import com.family.petmemory.entity.pet.Ingredient;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class RecipeForm {

    @NotNull
    private Ingredient ingredient;

    @DecimalMin(value = "0.00", inclusive = false)
    @Digits(integer=2, fraction=2)
    private BigDecimal percentage;

}
