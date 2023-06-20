package com.easydiet.api.rest.recipe_entry;

import com.easydiet.domain.recipe_entry.RecipeEntry;
import lombok.Data;

@Data
public class RecipeEntryBrief {
    private String directory_id;
    private String id;
    private String name;
    private String content;

    public RecipeEntryBrief(String directory_id, String id, String name, String content) {
        this.directory_id = directory_id;
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public static RecipeEntryBrief from(RecipeEntry recipeEntry) {
        return new RecipeEntryBrief(recipeEntry.getDirectory_id(),
                recipeEntry.getId().getId(),
                recipeEntry.getName().getName(),
                recipeEntry.getContent().getContent()
        );
    }
}
