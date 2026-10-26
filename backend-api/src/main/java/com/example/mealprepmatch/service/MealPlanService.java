package com.example.mealprepmatch.service;
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

    public MealPlan create(Long consumerId, Long chefId, List<Long> mealIds, MealPlan incoming) {
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
        plan.setMealsThisWeek(meals);
        return mealPlanRepo.save(plan);
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

    public void delete(Long id) {
        if (!mealPlanRepo.existsById(id)) {
            throw new NotFoundException("Meal plan not found");
        }
        mealPlanRepo.deleteById(id);
    }
}