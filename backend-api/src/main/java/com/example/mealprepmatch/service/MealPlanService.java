package com.example.mealprepmatch.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.mealprepmatch.entity.Consumer;
import com.example.mealprepmatch.entity.Meal;
import com.example.mealprepmatch.entity.MealPlan;
import com.example.mealprepmatch.repository.ConsumerRepo;
import com.example.mealprepmatch.repository.MealPlanRepo;
import com.example.mealprepmatch.repository.MealRepo;

@Service
@Transactional
public class MealPlanService {

    private final MealPlanRepo mealPlanRepo;
    private final ConsumerRepo consumerRepo;
    private final MealRepo mealRepo;

    public MealPlanService(MealPlanRepo mealPlanRepo, ConsumerRepo consumerRepo, MealRepo mealRepo) {
        this.mealPlanRepo = mealPlanRepo;
        this.consumerRepo = consumerRepo;
        this.mealRepo = mealRepo;
    }

    public MealPlan create(Long consumerId, List<Long> mealIds, MealPlan incoming) {
        Consumer consumer = consumerRepo.findById(consumerId)
                .orElseThrow(() -> new NotFoundException("Consumer not found"));

        List<Meal> meals = mealRepo.findAllById(mealIds);
        if (meals.size() != mealIds.stream().distinct().count()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "One or more meals not found");
        }

        MealPlan plan = new MealPlan();
        plan.setWeeks(incoming.getWeeks());
        plan.setDescription(incoming.getDescription());
        plan.setCalories(incoming.getCalories());
        plan.setConsumer(consumer);
        // findAllById can return an immutable empty list; copy so the plan's meals stay modifiable
        plan.setMealsThisWeek(new ArrayList<>(meals));
        return mealPlanRepo.save(plan);
    }

    // UC2: add one meal to an existing plan
    // TODO: add consumerId parameter + ownership check, blacklist rule, calorie recalculation
    public MealPlan addMeal(Long planId, Long mealId) {
        MealPlan plan = mealPlanRepo.findById(planId)
                .orElseThrow(() -> new NotFoundException("Meal plan not found"));
        Meal meal = mealRepo.findById(mealId)
                .orElseThrow(() -> new NotFoundException("Meal not found"));

        boolean alreadyAdded = plan.getMealsThisWeek().stream()
                .anyMatch(m -> m.getId().equals(mealId));
        if (alreadyAdded) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "That meal is already in this plan");
        }

        plan.getMealsThisWeek().add(meal);
        return plan;   // managed entity, saved when the transaction commits
    }

    // UC2: remove one meal from a plan
    // TODO: add consumerId parameter + ownership check, calorie recalculation
    public MealPlan removeMeal(Long planId, Long mealId) {
        MealPlan plan = mealPlanRepo.findById(planId)
                .orElseThrow(() -> new NotFoundException("Meal plan not found"));

        boolean removed = plan.getMealsThisWeek().removeIf(m -> m.getId().equals(mealId));
        if (!removed) {
            throw new NotFoundException("That meal is not in this plan");
        }
        return plan;
    }

    @Transactional(readOnly = true)
    public MealPlan get(Long id) {
        return mealPlanRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Meal plan not found"));
    }

    @Transactional(readOnly = true)
    public List<MealPlan> listForConsumer(Long consumerId) {
        return mealPlanRepo.findByConsumerId(consumerId);
    }

    // All meals, A-Z, for the "add a meal" list. Swap for MealService if you have one.
    @Transactional(readOnly = true)
    public List<Meal> listMealCatalog() {
        return mealRepo.findAllByOrderByNameAsc();
    }

    public void delete(Long id) {
        if (!mealPlanRepo.existsById(id)) {
            throw new NotFoundException("Meal plan not found");
        }
        mealPlanRepo.deleteById(id);
    }
}