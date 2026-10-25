package com.example.mealprepmatch.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.mealprepmatch.entity.Chef;
import com.example.mealprepmatch.entity.Meal;
import com.example.mealprepmatch.repository.ChefRepo;
import com.example.mealprepmatch.repository.MealRepo;

@Service
public class AddMealsService {

    private final MealRepo mealRepo;
    private final ChefRepo chefRepo;

    public AddMealsService(MealRepo mealRepo, ChefRepo chefRepo) {
        this.mealRepo = mealRepo;
        this.chefRepo = chefRepo;
    }

    // Chef adds a meal to the catalog
    public Meal addMeal(Long chefId, Meal meal) {
        Chef chef = chefRepo.findById(chefId)
                .orElseThrow(() -> new NotFoundException("Chef not found"));

        meal.setChef(chef);
        return mealRepo.save(meal);
    }

    // View all meals
    public List<Meal> getAllMeals() {
        return mealRepo.findAll();
    }

    // Delete a meal
    public void deleteMeal(Long mealId) {
        if (!mealRepo.existsById(mealId)) {
            throw new NotFoundException("Meal not found");
        }
        mealRepo.deleteById(mealId);
    }
}
