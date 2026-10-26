
package com.example.mealprepmatch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mealprepmatch.entity.MealPlan;

public interface MealPlanRepo extends JpaRepository<MealPlan, Long> {
    List<MealPlan> findByConsumerId(Long consumerId);
}
