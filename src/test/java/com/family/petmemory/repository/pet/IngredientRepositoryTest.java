package com.family.petmemory.repository.pet;

import com.family.petmemory.entity.pet.Ingredient;
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
class IngredientRepositoryTest {

    @Autowired
    IngredientRepository ingredientRepository;

    @Test
    @Rollback(value = false)
    void basicTest() {
        //given
        Ingredient ingredient = new Ingredient("조단백질");
        Ingredient ingredient1 = ingredientRepository.save(ingredient);

        //when
        Ingredient find = ingredientRepository.findById(ingredient1.getId()).get();

        //then
        assertThat(ingredient.getId()).isEqualTo(find.getId());
    }
}