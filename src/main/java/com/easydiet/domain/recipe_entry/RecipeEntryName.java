package com.easydiet.domain.recipe_entry;

public class RecipeEntryName {
    private static final int MAX_LENGTH = 200;
    private final String name;

    public RecipeEntryName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RecipeEntryName(" + name + ")";
    }

    public String getName() {
        return name;
    }
    public static RecipeEntryName create(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalStateException("Название рецепта не может быть пустым.");
        }
        if (name.length() > MAX_LENGTH) {
            throw new IllegalStateException("Название рецепта не должно быть длиннее " + MAX_LENGTH + " символов");
        }
        return new RecipeEntryName(name);
    }
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (!(other instanceof RecipeEntryName otherName)) {
            return false;
        }
        return this.name.equals(otherName.name);
    }
}
