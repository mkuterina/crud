package com.easydiet.domain.recipe_line_attribute;

public class RecipeLineAttributeValue {

    private final String value;

    public RecipeLineAttributeValue(String value) {
        this.value = value;
    }
   
    @Override
    public String toString() {
        return "RecipeLineAttributeValue(" + value + ")";
    }

    public String getValue() {
        return value;
    }
    public static RecipeLineAttributeValue create(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Добавьте значение атрибута");
        }
        return new RecipeLineAttributeValue(value);
    }
}
