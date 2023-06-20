package com.easydiet.domain.recipe_line;

import lombok.Getter;

@Getter
public class RecipeLineType {

    private final String type;

    public RecipeLineType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "RecipeLineType(" + type + ")";
    }

    public static RecipeLineType create(String type) {
        if (type == null || type.isBlank()) {
            throw new IllegalStateException("Укажите тип связи с рецептом.");
        }
        return new RecipeLineType(type);
    }

}
