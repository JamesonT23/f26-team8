package com.example.mealprepmatch.controller;

import com.example.mealprepmatch.entity.Chef;
import com.example.mealprepmatch.service.ChefProfile;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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