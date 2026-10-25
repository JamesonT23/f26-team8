package com.example.mealprepmatch.controller;

import java.util.List;
import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.mealprepmatch.entity.DietTags;
import com.example.mealprepmatch.entity.Meal;
import com.example.mealprepmatch.entity.MealPlan;
import com.example.mealprepmatch.service.AddMealsService;
import com.example.mealprepmatch.service.PantryService;

import jakarta.validation.Valid;


@RestController 
@RequestMapping("/api/meals")
public class MealController {
    private final AddMealsService addMealsService;
    private final PantryService pantryService;

    public MealController(AddMealsService addMealsService, PantryService pantryService) {
        this.addMealsService = addMealsService;
        this.pantryService = pantryService;
    }

    @PostMapping
    public ResponseEntity<MealPlan> add(@PathVariable Long chefId, @Valid @RequestBody MealPlan plan) {
        MealPlan created = addMealsService.addMeal(chefId, plan);
        return ResponseEntity
                .created(URI.create("/api/chefs/" + chefId + "/meal-plans/" + created.getId()))
                .body(created);
    }

    @DeleteMapping("/{mealPlanId}")
    public ResponseEntity<Void> delete(@PathVariable Long chefId, @PathVariable Long mealPlanId) {
        addMealsService.deleteMeal(chefId, mealPlanId);
        return ResponseEntity.noContent().build();
    }

}