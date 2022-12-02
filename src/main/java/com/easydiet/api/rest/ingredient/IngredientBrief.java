package com.easydiet.api.rest.ingredient;

import com.easydiet.domain.ingredient.Ingredient;
import lombok.Data;

@Data
public class IngredientBrief {

    private String id;
    private String name;

    private IngredientBrief(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static IngredientBrief from(Ingredient ingredient) {
        return new IngredientBrief(ingredient.getId(), ingredient.getName());
    }
}
