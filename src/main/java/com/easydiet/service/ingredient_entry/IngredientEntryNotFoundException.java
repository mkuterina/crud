package com.easydiet.service.ingredient_entry;

public class IngredientEntryNotFoundException extends Exception {
    public IngredientEntryNotFoundException(String id) {

        super("Ингридиент " + id + " не найден");
    }
}
