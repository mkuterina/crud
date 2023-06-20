package com.easydiet.domain.ingredient_entry;

public class IngredientEntryDescription {
    private final String description;

    public IngredientEntryDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "IngredientEntryDescription(" + description + ")";
    }

    public String getDescription() {
        return description;
    }

    public static IngredientEntryDescription create(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalStateException("Добавьте описание ингредиента.");
        }
        return new IngredientEntryDescription(description);
    }
}
