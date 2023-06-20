package com.easydiet.api.rest.recipe_entry;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RenameRecipeEntryCommandResult {
    private String status;
    private Boolean result;
    private String message;

    public RenameRecipeEntryCommandResult(String status, Boolean result, String message) {
        this.status = status;
        this.result = result;
        this.message = message;
    }

    public static RenameRecipeEntryCommandResult success(boolean result) {
        return new RenameRecipeEntryCommandResult("success", result, null);
    }

    public static RenameRecipeEntryCommandResult fail(String message) {
        return new RenameRecipeEntryCommandResult("fail", null, message);
    }
}
