package com.easydiet.api.rest.recipe_line;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeleteRecipeLineCommandResult {
    private String status;
    private Boolean result;
    private String message;

    public DeleteRecipeLineCommandResult(String status, Boolean result, String message) {
        this.status = status;
        this.result = result;
        this.message = message;
    }

    public static DeleteRecipeLineCommandResult success(boolean result) {
        return new DeleteRecipeLineCommandResult("success", result, null);
    }

    public static DeleteRecipeLineCommandResult fail(String message) {
        return new DeleteRecipeLineCommandResult("fail", null, message);
    }
}
