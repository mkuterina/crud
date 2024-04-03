package com.easydiet.domain.ingredient_entry;

import lombok.Getter;

@Getter

public class IngredientEntryName {
    private static final int MAX_LENGTH = 100;
    private final String name;

    private IngredientEntryName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "IngredientEntryName(" + name + ")";
    }

       public static IngredientEntryName create(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalStateException("Имя ингридиента не может быть пустым");
        }
        if (name.length() > MAX_LENGTH) {
            throw new IllegalStateException("Имя ингридиента не должно быть длиннее " + MAX_LENGTH + " символов");
        }
        return new IngredientEntryName(name);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (!(other instanceof IngredientEntryName otherName)) {
            return false;
        }
        return this.name.equals(otherName.name);
    }
}
