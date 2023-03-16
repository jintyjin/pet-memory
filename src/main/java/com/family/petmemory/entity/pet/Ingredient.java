package com.family.petmemory.entity.pet;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Ingredient {

    @Id
    @GeneratedValue
    @Column(name = "ingredient_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "ingredient")
    private List<Recipe> recipes = new ArrayList<>();

    protected Ingredient() {
    }

    public Ingredient(String name) {
        this.name = name;
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }
}
