package com.example.mealprepmatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mealprepmatch.entity.Meal;

public interface MealRepo extends JpaRepository<Meal, Long> {
    
}