package com.example.mealprepmatch.repository;

import com.example.mealprepmatch.entity.Chef;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChefRepo extends JpaRepository<Chef, Long> {
}