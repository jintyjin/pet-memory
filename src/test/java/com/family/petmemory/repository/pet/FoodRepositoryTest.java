package com.family.petmemory.repository.pet;

import com.family.petmemory.entity.pet.Food;
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
class FoodRepositoryTest {

    @Autowired
    FoodRepository foodRepository;

    @Test
    @Rollback(value = false)
    void basicTest() {
        //given
        Food food = new Food("사료1");

        //when
        Food savedFood = foodRepository.save(food);
        Food findFood = foodRepository.findById(savedFood.getId()).get();

        //then
        assertThat(findFood.getId()).isEqualTo(food.getId());
    }
}