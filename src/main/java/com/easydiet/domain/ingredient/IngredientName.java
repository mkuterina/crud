package com.easydiet.domain.ingredient;

public class IngredientName {

    private static final int MAX_LENGTH = 100;

    private final String name;

    private IngredientName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "IngredientName(" + name + ")";
    }

    public String getName() {
        return name;
    }

    public static IngredientName create(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalStateException("Имя ингридиента не может быть пустым");
        }
        if (name.length() > MAX_LENGTH) {
            throw new IllegalStateException("Имя ингридиента не должно быть длиннее " + MAX_LENGTH + " символов");
        }
        return new IngredientName(name);
    }
}
