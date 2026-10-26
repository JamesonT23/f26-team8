package com.example.mealprepmatch.controller;
import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mealprepmatch.entity.MealPlan;
import com.example.mealprepmatch.service.MealPlanService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/meal-plans")
public class MealPlanController {

    private final MealPlanService service;

    public MealPlanController(MealPlanService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MealPlan> create(@RequestParam Long consumerId, @RequestParam Long chefId, @RequestParam List<Long> mealIds, @Valid @RequestBody MealPlan plan) {
        MealPlan created = service.create(consumerId, chefId, mealIds, plan);
        return ResponseEntity.created(URI.create("/api/meal-plans/" + created.getId())).body(created);
    }

    @GetMapping("/{id}")
    public MealPlan get(@PathVariable Long id) {
        return service.get(id);
    }

    // GET /api/meal-plans?consumerId=1   or   ?chefId=2
    @GetMapping
    public List<MealPlan> list(@RequestParam(required = false) Long consumerId, @RequestParam(required = false) Long chefId) {
        if (consumerId != null) return service.listForConsumer(consumerId);
        throw new org.springframework.web.server.ResponseStatusException(
                org.springframework.http.HttpStatus.BAD_REQUEST, "Provide consumerId or chefId");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
