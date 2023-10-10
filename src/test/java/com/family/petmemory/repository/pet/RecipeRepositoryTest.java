package com.family.petmemory.repository.pet;

import com.family.petmemory.infra.ImageSize;
import com.family.petmemory.entity.pet.Food;
import com.family.petmemory.entity.pet.Ingredient;
import com.family.petmemory.entity.pet.Recipe;
import com.family.petmemory.infra.UploadFile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class RecipeRepositoryTest {

    @Autowired
    FoodRepository foodRepository;
    @Autowired
    RecipeRepository recipeRepository;

    @Test
    @Rollback(value = false)
    void basicTest() {
        //given
        Food food = new Food("사료1", new UploadFile("uploadFile", "saveFile"), new ImageSize(1280, 960));
        foodRepository.save(food);
        Recipe recipe = new Recipe(food, Ingredient.BEEF, new BigDecimal(56.08));

        //when
        Recipe savedRecipe = recipeRepository.save(recipe);
        Recipe findRecipe = recipeRepository.findById(savedRecipe.getId()).get();

        //then
        assertThat(findRecipe.getId()).isEqualTo(recipe.getId());
        assertThat(food.getRecipes().size()).isEqualTo(1);
        for (Recipe foodRecipe : food.getRecipes()) {
            System.out.println(foodRecipe.getFood().getId());
            System.out.println(foodRecipe.getFood().getName());
            System.out.println(foodRecipe.getFood().getFoodStatus());
            System.out.println(foodRecipe.getFood().getCreatedDate());
        }
     }
}