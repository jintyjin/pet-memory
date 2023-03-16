package com.family.petmemory.entity.pet;

import com.family.petmemory.entity.base.BaseTimeEntity;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Food extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "food_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "food")
    private List<Recipe> recipes = new ArrayList<>();

    protected Food() {
    }

    public Food(String name) {
        this.name = name;
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }
}
