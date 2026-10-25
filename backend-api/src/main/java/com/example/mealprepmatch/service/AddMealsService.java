package com.example.mealprepmatch.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.mealprepmatch.entity.Chef;
import com.example.mealprepmatch.entity.MealPlan;
import com.example.mealprepmatch.repository.ChefRepo;
import com.example.mealprepmatch.repository.MealPlanRepo;

@Service
public class AddMealsService {

    private final MealPlanRepo mealPlanRepo;
    private final ChefRepo chefRepo;

    public AddMealsService(
            MealPlanRepo mealPlanRepo,
            ChefRepo chefRepo) {
        this.mealPlanRepo = mealPlanRepo;
        this.chefRepo = chefRepo;
    }

    public List<MealPlan> getMeals(Long chefId) {
        if (!chefRepo.existsById(chefId)) {
            throw new NotFoundException("Chef not found");
        }

        return mealPlanRepo.findByChefId(chefId);
    }

    public MealPlan addMeal(Long chefId, MealPlan meal) {
        Chef chef = chefRepo.findById(chefId)
                .orElseThrow(() -> new NotFoundException("Chef not found"));

        meal.setChef(chef);
        return mealPlanRepo.save(meal);
    }

    public void deleteMeal(Long mealId, Long mealPlanId) {
        MealPlan meal = mealPlanRepo.findById(mealId)
                .orElseThrow(() -> new NotFoundException("Meal not found"));

        mealPlanRepo.delete(meal);
    }
}