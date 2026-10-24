package com.example.mealprepmatch.service;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}