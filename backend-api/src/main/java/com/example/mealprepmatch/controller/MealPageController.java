
package com.example.mealprepmatch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.mealprepmatch.service.AddMealsService;

@Controller
public class MealPageController {

    private final AddMealsService addMealsService;

    public MealPageController(AddMealsService addMealsService) {
        this.addMealsService = addMealsService;
    }

    @GetMapping("/meals/{mealId}")
    public String showMealDetails(
            @PathVariable Long mealId,
            Model model) {

        model.addAttribute("meal",
                addMealsService.getMealById(mealId));

        return "meal-details";
    }
}
