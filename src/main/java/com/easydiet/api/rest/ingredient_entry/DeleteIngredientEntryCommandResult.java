package com.easydiet.api.rest.ingredient_entry;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeleteIngredientEntryCommandResult {
    private String status;
    private Boolean result;
    private String message;

    public DeleteIngredientEntryCommandResult(String status, Boolean result, String message) {
        this.status = status;
        this.result = result;
        this.message = message;
    }
    public static DeleteIngredientEntryCommandResult success(boolean result) {
        return new DeleteIngredientEntryCommandResult("success", result, null);
    }

    public static DeleteIngredientEntryCommandResult fail(String message) {
        return new DeleteIngredientEntryCommandResult("fail", null, message);
    }
}
