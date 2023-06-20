package com.easydiet.api.rest.recipe_line;

import com.easydiet.domain.recipe_line.RecipeLine;
import lombok.Data;

@Data
public class RecipeLineBrief {
    private String id;
    private String recipe_entry_id;
    private String directory_id;
    private String type;

    public RecipeLineBrief(String id, String recipe_entry_id, String directory_id, String type) {
        this.id = id;
        this.recipe_entry_id = recipe_entry_id;
        this.directory_id = directory_id;
        this.type = type;
    }

    public static RecipeLineBrief from(RecipeLine recipeLine) {
        return new RecipeLineBrief(recipeLine.getId().getId(),
                recipeLine.getRecipe_entry_id(),
                recipeLine.getDirectory_id(),
                recipeLine.getType().getType()
        );
    }
}
