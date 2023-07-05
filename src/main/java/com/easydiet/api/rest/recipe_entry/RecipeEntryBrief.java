package com.easydiet.api.rest.recipe_entry;

import com.easydiet.domain.recipe_entry.RecipeEntry;
import lombok.Data;

@Data
public class RecipeEntryBrief {
    private String directoryId;
    private String id;
    private String name;
    private String content;

    public RecipeEntryBrief(String directory_id, String id, String name, String content) {
        this.directoryId = directory_id;
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public static RecipeEntryBrief from(RecipeEntry recipeEntry) {
        return new RecipeEntryBrief(recipeEntry.getDirectoryId(),
                recipeEntry.getId().getId(),
                recipeEntry.getName().getName(),
                recipeEntry.getContent().getContent()
        );
    }
}
