package com.easydiet.service.recipe_entry;

public class RecipeEntryNotFoundException extends Exception {
    public RecipeEntryNotFoundException(String id) {
        super("Рецепт " + id + " не найден");
    }
}
