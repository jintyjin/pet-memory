package com.family.petmemory.repository.pet;

import com.family.petmemory.entity.dto.FoodDto;
import com.family.petmemory.entity.dto.SearchFoodCondition;
import com.family.petmemory.entity.pet.Ingredient;
import com.family.petmemory.entity.pet.Recipe;
import com.family.petmemory.infra.ImageSize;
import com.family.petmemory.entity.pet.Food;
import com.family.petmemory.infra.UploadFile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class FoodRepositoryTest {

    @Autowired
    FoodRepository foodRepository;
    @Autowired
    RecipeRepository recipeRepository;

    @Test
    @Rollback(value = false)
    void basicTest() {
        //given
        Food food = new Food("사료1", new UploadFile("uploadFile", "saveFile"), new ImageSize(1280, 960));

        //when
        Food savedFood = foodRepository.save(food);
        Food findFood = foodRepository.findById(savedFood.getId()).get();

        //then
        assertThat(findFood.getId()).isEqualTo(food.getId());
    }

    @Test
    @Rollback(value = false)
    void 음식_검색_테스트() {
        //given
        Food food = new Food("사료1", new UploadFile("uploadFile", "saveFile"), new ImageSize(1280, 960));
        Food food1 = new Food("사료2", new UploadFile("uploadFile1", "saveFile1"), new ImageSize(1280, 960));
        foodRepository.save(food);
        foodRepository.save(food1);
        Recipe recipe = new Recipe(food, Ingredient.BEEF, new BigDecimal("4.825"));
        Recipe recipe1 = new Recipe(food, Ingredient.PORK, new BigDecimal("5.236"));
        Recipe recipe2 = new Recipe(food, Ingredient.SALMON, new BigDecimal("4.248"));
        Recipe recipe3 = new Recipe(food, Ingredient.CHICKEN_FAT, new BigDecimal("3.510"));
        Recipe recipe4 = new Recipe(food, Ingredient.REFINED_SALT, new BigDecimal("2.425"));
        Recipe recipe5 = new Recipe(food1, Ingredient.PORK, new BigDecimal("5.236"));
        Recipe recipe6 = new Recipe(food1, Ingredient.SALMON, new BigDecimal("3.510"));
        recipeRepository.save(recipe);
        recipeRepository.save(recipe1);
        recipeRepository.save(recipe2);
        recipeRepository.save(recipe3);
        recipeRepository.save(recipe4);
        recipeRepository.save(recipe5);
        recipeRepository.save(recipe6);

        //when
        List<Ingredient> ingredientList = new ArrayList<>();
        List<Ingredient> ingredientList1 = new ArrayList<>();
        ingredientList.add(Ingredient.PORK);
        ingredientList.add(Ingredient.POULTRY_MANURE);
        ingredientList.add(Ingredient.CALCIUM);
        SearchFoodCondition condition = new SearchFoodCondition("2", ingredientList);
        ingredientList1.add(Ingredient.BEEF);
        SearchFoodCondition condition1 = new SearchFoodCondition("사", ingredientList1);
        Page<FoodDto> searchFood = foodRepository.searchFood(condition, PageRequest.of(0, 10));
        Page<FoodDto> searchFood1 = foodRepository.searchFood(condition1, PageRequest.of(0, 10));
        Food findFood = foodRepository.findById(food.getId()).get();
        Food findFood1 = foodRepository.findById(food1.getId()).get();

        //then
        searchFood.getContent().stream().forEach(foodDto -> System.out.println(foodDto.getName() + " : " + foodDto.getPath()));
        System.out.println("----------------------------------------");
        searchFood1.getContent().stream().forEach(foodDto -> System.out.println(foodDto.getName() + " : " + foodDto.getPath()));
        System.out.println("----------------------------------------");
        assertThat(searchFood.getContent().size()).isEqualTo(0);
        assertThat(searchFood1.getContent().size()).isEqualTo(1);
    }
}