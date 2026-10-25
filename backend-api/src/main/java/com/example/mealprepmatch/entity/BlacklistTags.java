package com.example.mealprepmatch.entity;

public enum BlacklistTags {
    DAIRY("Dairy"),
    EGGS("Eggs"),
    PEANUTS("Peanuts"),
    TREE_NUTS("Tree Nuts"),
    SOY("Soy"),
    GLUTEN("Gluten"),
    FISH("Fish"),
    SHELLFISH("Shellfish"),
    SESAME("Sesame"),
    PORK("Pork");

    private final String displayName;

    BlacklistTags(String displayName) { this.displayName = displayName; }

    public String getDisplayName() { return displayName; }
}
