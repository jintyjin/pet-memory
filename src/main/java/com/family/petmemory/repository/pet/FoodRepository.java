package com.family.petmemory.repository.pet;

import com.family.petmemory.entity.pet.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long>, CustomFoodRepository {
}
