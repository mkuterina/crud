package com.easydiet.service.ingredient;

public class IngredientNotFoundException extends Exception {
    public IngredientNotFoundException(String id) {
        super("Ингридиент " + id + " не найден");
    }
}
