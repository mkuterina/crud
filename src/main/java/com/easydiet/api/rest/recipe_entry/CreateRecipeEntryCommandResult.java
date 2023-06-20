package com.easydiet.api.rest.recipe_entry;

import com.easydiet.domain.recipe_entry.RecipeEntry;
import com.easydiet.domain.recipe_entry.RecipeEntryId;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateRecipeEntryCommandResult {
    private String status;
    private RecipeEntryId id;
    private String message;

    public CreateRecipeEntryCommandResult(String status, RecipeEntryId id, String message) {
        this.status = status;
        this.id = id;
        this.message = message;
    }
    public static CreateRecipeEntryCommandResult success(RecipeEntry recipeEntry) {
        return new CreateRecipeEntryCommandResult("success", recipeEntry.getId(), null);
    }

    public static CreateRecipeEntryCommandResult fail(String message) {
        return new CreateRecipeEntryCommandResult("fail", null, message);
    }
}
