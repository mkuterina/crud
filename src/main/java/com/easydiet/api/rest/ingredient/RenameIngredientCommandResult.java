package com.easydiet.api.rest.ingredient;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RenameIngredientCommandResult {
    private String status;
    private Boolean result;
    private String message;

    private RenameIngredientCommandResult(String status, Boolean result, String message) {
        this.status = status;
        this.result = result;
        this.message = message;
    }

    public static RenameIngredientCommandResult success(boolean result) {
        return new RenameIngredientCommandResult("success", result, null);
    }

    public static RenameIngredientCommandResult fail(String message) {
        return new RenameIngredientCommandResult("fail", null, message);
    }
}
