package com.example.mealprepmatch.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.mealprepmatch.entity.Meal;
import com.example.mealprepmatch.service.AddMealsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    private final AddMealsService addMealsService;

    public MealController(AddMealsService addMealsService) {
        this.addMealsService = addMealsService;
    }

    // View all meals
    @GetMapping
    public List<Meal> getAllMeals() {
        return addMealsService.getAllMeals();
    }

    // Chef adds a meal
    @PostMapping("/chef/{chefId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Meal addMeal(@PathVariable Long chefId, @Valid @RequestBody Meal meal) {
        return addMealsService.addMeal(chefId, meal);
    }

    // Delete a meal
    @DeleteMapping("/{mealId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMeal(@PathVariable Long mealId) {
        addMealsService.deleteMeal(mealId);
    }
}
