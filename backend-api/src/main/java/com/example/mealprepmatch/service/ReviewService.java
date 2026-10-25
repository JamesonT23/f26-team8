package com.example.mealprepmatch.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.mealprepmatch.entity.Chef;
import com.example.mealprepmatch.entity.Consumer;
import com.example.mealprepmatch.entity.Review;
import com.example.mealprepmatch.repository.ChefRepo;
import com.example.mealprepmatch.repository.ConsumerRepo;
import com.example.mealprepmatch.repository.ReviewRepo;

@Service
public class ReviewService {

    private final ReviewRepo reviewRepo;
    private final ChefRepo chefRepo;
    private final ConsumerRepo consumerRepo;

    public ReviewService(
            ReviewRepo reviewRepo,
            ChefRepo chefRepo,
            ConsumerRepo consumerRepo) {
        this.reviewRepo = reviewRepo;
        this.chefRepo = chefRepo;
        this.consumerRepo = consumerRepo;
    }

    public List<Review> getAllReviews() {
        return reviewRepo.findAll();
    }

    public Review getReviewById(Long id) {
        return reviewRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Review not found"));
    }

    public Review addReview(
            Long chefId, Long consumerId, Review review) {

        Chef chef = chefRepo.findById(chefId)
                .orElseThrow(() -> new NotFoundException("Chef not found"));

        Consumer consumer = consumerRepo.findById(consumerId)
                .orElseThrow(() -> new NotFoundException("Consumer not found"));

        review.setChef(chef);
        review.setConsumer(consumer);

        return reviewRepo.save(review);
    }

    public void deleteReview(Long id) {
        Review review = getReviewById(id);
        reviewRepo.delete(review);
    }
}