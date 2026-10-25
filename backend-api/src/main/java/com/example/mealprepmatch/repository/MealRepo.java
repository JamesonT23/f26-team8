package com.example.mealprepmatch.repository;

import com.example.mealprepmatch.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepo extends JpaRepository<Meal, Long> {

}
