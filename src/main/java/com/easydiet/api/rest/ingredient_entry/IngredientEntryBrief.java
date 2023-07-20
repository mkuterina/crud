package com.easydiet.api.rest.ingredient_entry;

import com.easydiet.domain.ingredient_entry.IngredientEntry;
import lombok.Data;

@Data
public class IngredientEntryBrief {
    private String directoryId;
    private String id;
    private String name;
    private String description;

    public IngredientEntryBrief(String directoryId, String id,
                                String name, String description) {
        this.directoryId = directoryId;
        this.id = id;
        this.name = name;
        this.description = description;

    }

      public static IngredientEntryBrief from(IngredientEntry ingredientEntry) {
        return new IngredientEntryBrief(ingredientEntry.getDirectoryId(),
                ingredientEntry.getId().getId(),
                ingredientEntry.getName().getName(),
                ingredientEntry.getDescription().getDescription()
        );
    }
}
