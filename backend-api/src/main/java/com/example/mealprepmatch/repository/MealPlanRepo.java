
package com.example.mealprepmatch.repository;

import com.example.mealprepmatch.entity.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealPlanRepo extends JpaRepository<MealPlan, Long> {

    List<MealPlan> findByChefId(Long chefId);

}
