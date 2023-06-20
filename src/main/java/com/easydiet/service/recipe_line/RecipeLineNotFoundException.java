package com.easydiet.service.recipe_line;

public class RecipeLineNotFoundException extends Exception {
    public RecipeLineNotFoundException(String id) {
        super( "Рецепт " + id + " не найден.");
        }
}
