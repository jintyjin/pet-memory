package com.family.petmemory.entity.pet;

import lombok.Getter;

import javax.persistence.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    protected Recipe() {
    }

    public Recipe(Food food, Ingredient ingredient) {
        this.food = food;
        this.ingredient = ingredient;
    }
}
