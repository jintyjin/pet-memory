package com.family.petmemory.repository.pet;

import com.family.petmemory.entity.pet.Food;
import com.family.petmemory.entity.pet.Ingredient;
import com.family.petmemory.entity.pet.Recipe;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RecipeRepositoryTest {

    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    FoodRepository foodRepository;
    @Autowired
    RecipeRepository recipeRepository;

    @Test
    @Rollback(value = false)
    void basicTest() {
        //given
        Ingredient ingredient = new Ingredient("조단백질");
        ingredientRepository.save(ingredient);
        Food food = new Food("사료1");
        foodRepository.save(food);
        Recipe recipe = new Recipe(food, ingredient, 56.08);

        //when
        Recipe savedRecipe = recipeRepository.save(recipe);
        Recipe findRecipe = recipeRepository.findById(savedRecipe.getId()).get();

        //then
        assertThat(findRecipe.getId()).isEqualTo(recipe.getId());
    }
}