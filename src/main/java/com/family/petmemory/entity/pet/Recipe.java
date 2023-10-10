package com.family.petmemory.entity.pet;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
public class Recipe {

    @Id
    @GeneratedValue
    @Column(name = "FoodIngredient_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    @Enumerated(EnumType.STRING)
    private Ingredient ingredient;

    @Column(scale = 1)
    private BigDecimal percentage;

    protected Recipe() {
    }

    public Recipe(Food food, Ingredient ingredient, BigDecimal percentage) {
        this.food = food;
        this.ingredient = ingredient;
        this.percentage = percentage;
        this.food.addRecipe(this);
    }
}
