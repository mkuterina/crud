package com.easydiet.api.rest.recipe_entry;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeleteRecipeEntryCommandResult {
    private String status;
    private Boolean result;
    private String message;

    public DeleteRecipeEntryCommandResult(String status, Boolean result, String message) {
        this.status = status;
        this.result = result;
        this.message = message;
    }

    public static DeleteRecipeEntryCommandResult success(boolean result) {
        return new DeleteRecipeEntryCommandResult("success", result, null);
    }

    public static DeleteRecipeEntryCommandResult fail(String message) {
        return new DeleteRecipeEntryCommandResult("fail", null, message);
    }
}
