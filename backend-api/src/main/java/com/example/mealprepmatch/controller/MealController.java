
package com.example.mealprepmatch.controller;

import com.example.mealprepmatch.entity.Meal;
import com.example.mealprepmatch.service.AddMealsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Meal addMeal(
            @PathVariable Long chefId,
            @Valid @RequestBody Meal meal) {
        return addMealsService.addMeal(chefId, meal);
    }

    // Delete a meal
    @DeleteMapping("/{mealId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMeal(@PathVariable Long mealId) {
        addMealsService.deleteMeal(mealId);
    }
}
