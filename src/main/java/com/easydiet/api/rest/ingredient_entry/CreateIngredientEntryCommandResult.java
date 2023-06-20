package com.easydiet.api.rest.ingredient_entry;

import com.easydiet.domain.ingredient_entry.IngredientEntry;
import com.easydiet.domain.ingredient_entry.IngredientEntryId;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateIngredientEntryCommandResult {
    private String status;
    private IngredientEntryId id;
    private String message;

    public CreateIngredientEntryCommandResult(String status, IngredientEntryId id, String message) {
        this.status = status;
        this.id = id;
        this.message = message;
    }
    public static CreateIngredientEntryCommandResult success(IngredientEntry ingredientEntry) {
        return new CreateIngredientEntryCommandResult("success", ingredientEntry.getId(), null);
    }

    public static CreateIngredientEntryCommandResult fail(String message) {
        return new CreateIngredientEntryCommandResult("fail", null, message);
    }
}
