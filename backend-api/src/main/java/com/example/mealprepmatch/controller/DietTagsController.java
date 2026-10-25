package com.example.mealprepmatch.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mealprepmatch.entity.BlacklistTags;
import com.example.mealprepmatch.entity.DietTags;

@RestController 
@RequestMapping ("/api")
public class DietTagsController {
    @GetMapping("/diet-tags")
    public List<Map<String, String>> getDietTags() {
        return Arrays.stream(DietTags.values())
                .map(tag -> Map.of("value", tag.name(), "label", tag.getDisplayName()))
                .toList();
    }

    @GetMapping("/blacklist-tags")
    public List<Map<String, String>> getBlacklistTags() {
        return Arrays.stream(BlacklistTags.values())
                .map(tag -> Map.of("value", tag.name(), "label", tag.getDisplayName()))
                .toList();
    }

}
