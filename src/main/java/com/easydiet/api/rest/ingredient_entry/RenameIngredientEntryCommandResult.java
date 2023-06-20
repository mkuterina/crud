package com.easydiet.api.rest.ingredient_entry;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RenameIngredientEntryCommandResult {
    private String status;
    private Boolean result;
    private String message;

    public RenameIngredientEntryCommandResult(String status, Boolean result, String message) {
        this.status = status;
        this.result = result;
        this.message = message;
    }
    public static RenameIngredientEntryCommandResult success(boolean result) {
        return new RenameIngredientEntryCommandResult("success", result, null);
    }

    public static RenameIngredientEntryCommandResult fail(String message) {
        return new RenameIngredientEntryCommandResult("fail", null, message);
    }
}
