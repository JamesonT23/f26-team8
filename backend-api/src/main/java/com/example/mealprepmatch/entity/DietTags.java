package com.example.mealprepmatch.entity;

public enum DietTags {
    VEGAN("Vegan"),
    VEGETARIAN("Vegetarian"),
    PESCATARIAN("Pescatarian"),
    KETO("Keto"),
    PALEO("Paleo"),
    LOW_CARB("Low Carb"),
    LOW_FAT("Low Fat"),
    LOW_SODIUM("Low Sodium"),
    HIGH_PROTEIN("High Protein"),
    GLUTEN_FREE("Gluten Free"),
    DAIRY_FREE("Dairy Free"),
    NUT_FREE("Nut Free");

    private final String displayName;

    DietTags(String displayName) { this.displayName = displayName; }

    public String getDisplayName() { return displayName; }
}
