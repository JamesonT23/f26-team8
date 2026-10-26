package com.example.mealprepmatch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mealprepmatch.entity.Meal;

public interface MealRepo extends JpaRepository<Meal, Long> {

    List<Meal> findAllByOrderByNameAsc();

}