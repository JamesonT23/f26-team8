package com.example.mealprepmatch.entity;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "meal_plans")
public class MealPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String name;

    @Min (1)
    @Column(nullable = false)
    private Integer weeks;
@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "consumer_id", nullable = false)
    @JsonIgnore
    private Consumer consumer;

    @ManyToMany
    @JoinTable(name = "meal_plan_meals",
               joinColumns = @JoinColumn(name = "meal_plan_id"),
               inverseJoinColumns = @JoinColumn(name = "meal_id"))
    private List<Meal> mealsThisWeek = new ArrayList<>();

    public MealPlan() {
    }

    public Long getId() {
        return id;
    }

    public Integer getWeeks() {
        return weeks;
    }

    public void setWeeks(Integer weeks) {
        this.weeks = weeks;
    }

    public List<Meal> getMealsThisWeek() {
        return mealsThisWeek;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public void setMealsThisWeek(List<Meal> mealsThisWeek) {
        this.mealsThisWeek = mealsThisWeek;
    }

}