package com.example.mealprepmatch.entity;

import java.util.EnumSet;
import java.util.Set;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "meals")
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Meal name is required")
    @Column(nullable = false)
    private String name;

    @Column(length = 2000)
    private String description;

    @NotNull(message = "Calories are required")
    @PositiveOrZero(message = "Calories cannot be negative")
    @Column(nullable = false)
    private Double calories;

    @NotNull(message = "Carbohydrates are required")
    @PositiveOrZero(message = "Carbohydrates cannot be negative")
    @Column(nullable = false)
    private Integer carbohydrates;

    @NotNull(message = "Protein is required")
    @PositiveOrZero(message = "Protein cannot be negative")
    @Column(nullable = false)
    private Integer protein;

    @NotNull(message = "Fat is required")
    @PositiveOrZero(message = "Fat cannot be negative")
    @Column(nullable = false)
    private Integer fat;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "diet_tags", joinColumns = @JoinColumn(name = "meal_id"))
    @Column(name = "tag", length = 40)
    private Set<DietTags> dietTags = EnumSet.noneOf(DietTags.class);

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "blacklist_tags", joinColumns = @JoinColumn(name = "meal_id"))
    @Column(name = "tag", length = 40)
    private Set<BlacklistTags> blacklistTags = EnumSet.noneOf(BlacklistTags.class);

    @ManyToOne
    @JoinColumn(name = "chef_id")
    private Chef chef;

    public Meal() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public Integer getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Integer carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Integer getProtein() {
        return protein;
    }

    public void setProtein(Integer protein) {
        this.protein = protein;
    }

    public Integer getFat() {
        return fat;
    }

    public void setFat(Integer fat) {
        this.fat = fat;
    }

    public Set<DietTags> getDietTags() {
        return dietTags;
    }

    public void setDietTags(Set<DietTags> dietTags) {
        this.dietTags = dietTags;
    }

    public Set<BlacklistTags> getBlacklistTags() {
        return blacklistTags;
    }

    public void setBlacklistTags(Set<BlacklistTags> blacklistTags) {
        this.blacklistTags = blacklistTags;
    }

    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }
}
