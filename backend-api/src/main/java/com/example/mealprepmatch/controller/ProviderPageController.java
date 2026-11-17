package com.example.mealprepmatch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.mealprepmatch.service.AddMealsService;
import com.example.mealprepmatch.service.ChefProfile;

@Controller
public class ProviderPageController {

    private final AddMealsService addMealsService;
    private final ChefProfile chefProfile;

    public ProviderPageController(
            AddMealsService addMealsService,
            ChefProfile chefProfile) {
        this.addMealsService = addMealsService;
        this.chefProfile = chefProfile;
    }

    @GetMapping("/provider/{chefId}/meals")
    public String showMeals(
            @PathVariable Long chefId,
            Model model) {

        model.addAttribute("chef", chefProfile.getChefById(chefId));
        model.addAttribute("meals", addMealsService.getMealsByChef(chefId));

        return "provider-meals";
    }

    @GetMapping("/provider/{chefId}/meals/new")
public String showAddMealForm(@PathVariable Long chefId, Model model) {
    model.addAttribute("chef", chefProfile.getChefById(chefId));
    return "add-meal";
}

}
