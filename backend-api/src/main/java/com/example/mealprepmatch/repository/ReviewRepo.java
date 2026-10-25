package com.example.mealprepmatch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mealprepmatch.entity.Review;

public interface ReviewRepo extends JpaRepository<Review, Long> {

    List<Review> findByChefId(Long chefId);

    List<Review> findByConsumerId(Long consumerId);
}