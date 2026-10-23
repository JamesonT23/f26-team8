package com.example.mealprepmatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MealPrepMatch.entity.Consumer;

public interface ConsumerRepo extends JpaRepository<Consumer, Long> {
    
}
