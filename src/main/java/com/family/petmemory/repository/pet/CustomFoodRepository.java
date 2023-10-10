package com.family.petmemory.repository.pet;

import com.family.petmemory.entity.dto.FoodDto;
import com.family.petmemory.entity.dto.SearchFoodCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomFoodRepository {

    Page<FoodDto> searchFood(SearchFoodCondition searchFoodCondition, Pageable pageable);
}
