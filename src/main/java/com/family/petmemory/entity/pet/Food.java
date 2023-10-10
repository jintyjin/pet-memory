package com.family.petmemory.entity.pet;

import com.family.petmemory.entity.base.BaseTimeEntity;
import com.family.petmemory.infra.ImageSize;
import com.family.petmemory.infra.UploadFile;
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

    @Embedded
    private UploadFile uploadFile;

    @Embedded
    private ImageSize imageSize;

    @Enumerated(EnumType.STRING)
    private FoodStatus foodStatus;

    protected Food() {
    }

    public Food(String name, UploadFile uploadFile, ImageSize imageSize) {
        this.name = name;
        this.uploadFile = uploadFile;
        this.imageSize = imageSize;
        this.foodStatus = FoodStatus.NORMAL;
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }
}
