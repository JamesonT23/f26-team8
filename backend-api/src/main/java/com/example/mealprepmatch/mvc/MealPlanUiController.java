package com.example.mealprepmatch.mvc;   // adjust to match the folder you moved this into

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mealprepmatch.entity.Meal;
import com.example.mealprepmatch.entity.MealPlan;
import com.example.mealprepmatch.service.MealPlanService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Server-rendered pages for Use Case 1 (subscribe) and Use Case 2 (add/remove meals).
 */
@Controller
@RequestMapping("/ui/meal-plans")
public class MealPlanUiController {

    private static final String SESSION_KEY = "consumerId";
    private static final String LOGIN_REDIRECT = "redirect:/ui/consumers/register";

    private final MealPlanService mealPlanService;

    public MealPlanUiController(MealPlanService mealPlanService) {
        this.mealPlanService = mealPlanService;
    }

    // ---------- My plans ----------

    @GetMapping
    public String list(HttpSession session, Model model) {
        Long consumerId = currentConsumerId(session);
        if (consumerId == null) {
            return LOGIN_REDIRECT;
        }
        model.addAttribute("plans", mealPlanService.listForConsumer(consumerId));
        return "meal-plan/list";
    }

    // ---------- UC1: subscribe ----------

    @GetMapping("/new")
    public String newForm(HttpSession session, Model model) {
        if (currentConsumerId(session) == null) {
            return LOGIN_REDIRECT;
        }
        model.addAttribute("form", new SubscribeForm());
        return "meal-plan/new";
    }

    @PostMapping
    public String subscribe(@Valid @ModelAttribute("form") SubscribeForm form,
                            BindingResult result,
                            HttpSession session,
                            RedirectAttributes flash) {
        Long consumerId = currentConsumerId(session);
        if (consumerId == null) {
            return LOGIN_REDIRECT;
        }
        if (result.hasErrors()) {
            return "meal-plan/new";
        }

        MealPlan incoming = new MealPlan();
        incoming.setWeeks(form.getWeeks());
        incoming.setDescription(form.getDescription());
        incoming.setCalories(0);   // TODO: compute from meals later

        MealPlan created = mealPlanService.create(consumerId, List.of(), incoming);
        flash.addFlashAttribute("message", "You're subscribed! Now add some meals to your plan.");
        return "redirect:/ui/meal-plans/" + created.getId();
    }

    // ---------- UC2: view plan, add / remove meals ----------

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, HttpSession session, Model model) {
        if (currentConsumerId(session) == null) {
            return LOGIN_REDIRECT;
        }
        MealPlan plan = mealPlanService.get(id);   // TODO: ownership check

        Set<Long> inPlan = plan.getMealsThisWeek().stream()
                .map(Meal::getId)
                .collect(Collectors.toSet());
        List<Meal> available = mealPlanService.listMealCatalog().stream()
                .filter(meal -> !inPlan.contains(meal.getId()))
                .toList();

        model.addAttribute("plan", plan);
        model.addAttribute("availableMeals", available);
        return "meal-plan/detail";
    }

    @PostMapping("/{id}/meals/{mealId}")
    public String addMeal(@PathVariable Long id, @PathVariable Long mealId,
                          HttpSession session, RedirectAttributes flash) {
        if (currentConsumerId(session) == null) {
            return LOGIN_REDIRECT;
        }
        try {
            mealPlanService.addMeal(id, mealId);
            flash.addFlashAttribute("message", "Meal added to your plan.");
        } catch (ResponseStatusException e) {
            flash.addFlashAttribute("error", e.getReason());
        }
        return "redirect:/ui/meal-plans/" + id;
    }

    @PostMapping("/{id}/meals/{mealId}/remove")
    public String removeMeal(@PathVariable Long id, @PathVariable Long mealId, HttpSession session, RedirectAttributes flash) {
        if (currentConsumerId(session) == null) {
            return LOGIN_REDIRECT;
        }
        mealPlanService.removeMeal(id, mealId);
        flash.addFlashAttribute("message", "Meal removed from your plan.");
        return "redirect:/ui/meal-plans/" + id;
    }

    // ---------- Helpers ----------

    private Long currentConsumerId(HttpSession session) {
        return (Long) session.getAttribute(SESSION_KEY);
    }

    /** Only what the subscribe screen may set; calories and consumer are set server-side. */
    public static class SubscribeForm {

        @NotNull(message = "Choose how many weeks")
        @Min(1)
        @Max(52)
        private Integer weeks;

        @NotBlank(message = "Add a short description")
        @Size(max = 2000)
        private String description;

        public Integer getWeeks() { return weeks; }
        public void setWeeks(Integer weeks) { this.weeks = weeks; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }
}