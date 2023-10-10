package com.family.petmemory.repository.pet;

import com.family.petmemory.entity.dto.FoodDto;
import com.family.petmemory.entity.dto.QFoodDto;
import com.family.petmemory.entity.dto.SearchFoodCondition;
import static com.family.petmemory.entity.pet.QFood.*;

import static com.family.petmemory.entity.pet.QRecipe.*;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class CustomFoodRepositoryImpl implements CustomFoodRepository {

    private final int MAX_COUNT = 20;

    private final JPAQueryFactory jpaQueryFactory;

    public CustomFoodRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<FoodDto> searchFood(SearchFoodCondition searchFoodCondition, Pageable pageable) {
        List<FoodDto> foodDtoList = jpaQueryFactory
                .select(new QFoodDto(
                        food.id,
                        food.name,
                        food.uploadFile.saveFileName
                ))
                .from(food)
                .leftJoin(food.recipes, recipe)
                .where(
                        //recipe.food.name.contains(searchFoodCondition.getSearch())
                        //(recipe.ingredient.in(searchFoodCondition.getIngredients())).not()

                        recipe.ingredient.in(searchFoodCondition.getIngredients()).not()
                )
//                .orderBy(recipe.id.desc())
//                .offset(pageable.getOffset())
//                .limit(MAX_COUNT)
                .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(recipe.count())
                .from(recipe)
                .leftJoin(recipe.food, food)
                .where(
                        //recipe.food.name.contains(searchFoodCondition.getSearch())
                        //(recipe.ingredient.in(searchFoodCondition.getIngredients())).not()
                        recipe.ingredient.in(searchFoodCondition.getIngredients()).not()
                );

        return PageableExecutionUtils.getPage(foodDtoList, pageable, countQuery::fetchOne);
    }
}
