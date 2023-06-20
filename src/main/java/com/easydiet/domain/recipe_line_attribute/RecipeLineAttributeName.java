package com.easydiet.domain.recipe_line_attribute;

public class RecipeLineAttributeName {
    private static final int MAX_LENGTH = 200;

    private final String name;

    public RecipeLineAttributeName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RecipeLineAttributeName(" + name + ")";
    }

    public String getName() {
        return name;
    }

    public static RecipeLineAttributeName create(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalStateException("Имя аттрибута не может быть пустым. ");
        }
        if (name.length() > MAX_LENGTH) {
            throw new IllegalStateException("Наименование аттрибута не должно быть длиннее " + MAX_LENGTH + " символов.");
        }
        return new RecipeLineAttributeName(name);
    }
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (!(other instanceof RecipeLineAttributeName otherName)) {
            return false;
        }
        return this.name.equals(otherName.name);
    }
}
