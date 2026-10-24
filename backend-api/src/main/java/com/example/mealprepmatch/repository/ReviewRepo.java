package com.example.mealprepmatch.repository;

import com.example.mealprepmatch.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, Long> {

    List<Review> findByChefId(Long chefId);
}