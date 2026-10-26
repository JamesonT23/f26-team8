package com.example.mealprepmatch.entity;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "consumers")
public class Consumer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank 
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank 
    @Email
    @Size(max = 254)
    @Column(nullable = false, unique = true, length = 254)
    private String email;

    @Size(max = 20)
    @Column(length = 20)
    private String phoneNumber;

    @NotBlank 
    @Size(max = 255)
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotBlank 
    @Size(max = 30)
    @Column(nullable = false, length = 30)
    private String accountStatus;

    @ElementCollection(targetClass = DietTags.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "diet_tags", joinColumns = @JoinColumn(name = "consumer_id"))
    @Column(name = "tag", length = 40)
    private Set<DietTags> preferredTags = EnumSet.noneOf(DietTags.class);

    @ElementCollection(targetClass = BlacklistTags.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "blacklist_tags", joinColumns = @JoinColumn(name = "consumer_id"))
    @Column(name = "tag", length = 40)
    private Set<BlacklistTags> blacklistedTags = EnumSet.noneOf(BlacklistTags.class);

    @OneToMany(mappedBy = "consumer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<MealPlan> mealPlanList = new ArrayList<>();

    @OneToMany(mappedBy = "consumer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Review> reviews = new ArrayList<>();

    public Consumer() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Set<DietTags> getPreferredTags() {
        return preferredTags;
    }

    public void setPreferredTags(Set<DietTags> preferredTags) {
        this.preferredTags = preferredTags;
    }

    public Set<BlacklistTags> getBlacklistedTags() {
        return blacklistedTags;
    }

    public void setBlacklistedTags(Set<BlacklistTags> blacklistedTags) {
        this.blacklistedTags = blacklistedTags;
    }

    public List<MealPlan> getMealPlanList() {
        return mealPlanList;
    }

    public void setMealPlanList(List<MealPlan> mealPlanList) {
        this.mealPlanList = mealPlanList;
    }

    public List<Review> getReviews() {
        return reviews;
    }
}

