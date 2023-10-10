package com.family.petmemory.controller;

import com.family.petmemory.entity.dto.FoodForm;
import com.family.petmemory.entity.dto.SearchFoodCondition;
import com.family.petmemory.entity.pet.Food;
import com.family.petmemory.service.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.print.Pageable;

@Controller
@RequiredArgsConstructor
@RequestMapping("/foods")
@Slf4j
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("foodForm", new FoodForm());
        return "/pets/createFoodForm";
    }

    @GetMapping
    public String foods(Model model, @ModelAttribute("searchFoodCondition") SearchFoodCondition searchFoodCondition, Pageable pageable) {


        return "/pets/foods";
    }
}
