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

import com.example.mealprepmatch.entity.Chef;
import com.example.mealprepmatch.service.ChefProfile;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/chefs")
public class ChefController {

    private final ChefProfile chefProfile;

    public ChefController(ChefProfile chefProfile) {
        this.chefProfile = chefProfile;
    }

    @GetMapping
    public List<Chef> getAllChefs() {
        return chefProfile.getAllChefs();
    }

    @GetMapping("/{id}")
    public Chef getChefById(@PathVariable Long id) {
        return chefProfile.getChefById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Chef createChef(@Valid @RequestBody Chef chef) {
        return chefProfile.createChef(chef);
    }

    @PutMapping("/{id}")
    public Chef updateChef(
            @PathVariable Long id,
            @Valid @RequestBody Chef chef) {
        return chefProfile.updateChef(id, chef);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteChef(@PathVariable Long id) {
        chefProfile.deleteChef(id);
    }
}