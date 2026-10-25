package com.example.mealprepmatch.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mealprepmatch.entity.Consumer;
import com.example.mealprepmatch.entity.DietTags;
import com.example.mealprepmatch.entity.Meal;
import com.example.mealprepmatch.repository.MealRepo;

@Service 
@Transactional (readOnly = true)
public class PantryService {

    private final MealRepo mealRepo;
    private final ConsumerService consumerService;

    public PantryService(MealRepo mealRepo, ConsumerService consumerService) {
        this.mealRepo = mealRepo;
        this.consumerService = consumerService;
    }

    public List<Meal> listAll() {
        return mealRepo.findAllByOrderByNameAsc();
    }

    public List<Meal> listByDietTag(DietTags tag) {
        return mealRepo.findAll().stream()
                .filter(meal -> meal.getDietTags().contains(tag))
                .toList();
    }
    
    public List<Meal> listForConsumer(Long consumerId) {
        Consumer consumer = consumerService.getConsumerById(consumerId);

        return mealRepo.findAll().stream()
                .filter(meal -> Collections.disjoint(meal.getBlacklistTags(), consumer.getBlacklistedTags()))
                .filter(meal -> meal.getDietTags().containsAll(consumer.getPreferredTags()))
                .toList();
    }
}
