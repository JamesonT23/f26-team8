package com.example.mealprepmatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mealprepmatch.entity.Chef;

public interface ChefRepo extends JpaRepository<Chef, Long> {
    boolean existsByEmailIgnoreCase(String email);
}