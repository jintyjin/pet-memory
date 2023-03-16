package com.family.petmemory.repository.pet;

import com.family.petmemory.entity.pet.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
