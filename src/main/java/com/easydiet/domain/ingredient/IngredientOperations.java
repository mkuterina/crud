package com.easydiet.domain.ingredient;

import java.time.LocalDateTime;

public class IngredientOperations {
    public static Ingredient create(IngredientName name) {
        if (name == null) {
            throw new IllegalStateException("Имя ингридиента должно быть задано");
        }
        return new Ingredient(IngredientId.create(), name, LocalDateTime.now());
    }
}
