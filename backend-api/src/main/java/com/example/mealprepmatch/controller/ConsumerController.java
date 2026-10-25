package com.example.mealprepmatch.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.mealprepmatch.entity.Consumer;
import com.example.mealprepmatch.service.ConsumerService;

import jakarta.validation.Valid;
    
@RestController
@RequestMapping("/api/consumers")
public class ConsumerController {
    private final ConsumerService consumerService;

    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @GetMapping 
    public List<Consumer> getAllConsumers() {
        return consumerService.getAllConsumers();
    }

    @GetMapping ("/{id}")
    public Consumer getConsumerById(@PathVariable Long id) {
        return consumerService.getConsumerById(id);
    }

    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED)
    public Consumer createConsumer(@Valid @RequestBody Consumer consumer) {
        return consumerService.createConsumer(consumer);
    }

    @PutMapping ("/{id}")
    public Consumer updateConsumer(@PathVariable Long id, @Valid @RequestBody Consumer consumer) {
        return consumerService.updateConsumer(id, consumer);
    }

    @DeleteMapping ("/{id}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteConsumer(@PathVariable Long id) {
        consumerService.deleteConsumer(id);
    }

}
