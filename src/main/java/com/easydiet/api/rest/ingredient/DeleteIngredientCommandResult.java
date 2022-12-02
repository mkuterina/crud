package com.easydiet.api.rest.ingredient;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeleteIngredientCommandResult {
    private String status;
    private Boolean result;
    private String message;

    private DeleteIngredientCommandResult(String status, Boolean result, String message) {
        this.status = status;
        this.result = result;
        this.message = message;
    }

    public static DeleteIngredientCommandResult success(boolean result) {
        return new DeleteIngredientCommandResult("success", result, null);
    }

    public static DeleteIngredientCommandResult fail(String message) {
        return new DeleteIngredientCommandResult("fail", null, message);
    }
}
