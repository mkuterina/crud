package com.easydiet.api.rest.ingredient_entry;

import com.easydiet.domain.ingredient_entry.IngredientEntry;
import lombok.Data;

@Data
public class IngredientEntryBrief {
    private String directory_id;
    private String id;
    private String name;
    private String description;

    public IngredientEntryBrief(String directory_id, String id, String name, String description) {
        this.directory_id = directory_id;
        this.id = id;
        this.name = name;
        this.description = description;

    }

      public static IngredientEntryBrief from(IngredientEntry ingredientEntry) {
        return new IngredientEntryBrief(ingredientEntry.getDirectory_id(),
                ingredientEntry.getId().getId(),
                ingredientEntry.getName().getName(),
                ingredientEntry.getDescription().getDescription()
        );
    }
}
