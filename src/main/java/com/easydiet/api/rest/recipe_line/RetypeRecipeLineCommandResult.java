package com.easydiet.api.rest.recipe_line;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RetypeRecipeLineCommandResult {
    private String status;
    private Boolean result;
    private String message;

    public RetypeRecipeLineCommandResult(String status, Boolean result, String message) {
        this.status = status;
        this.result = result;
        this.message = message;
    }

    public static RetypeRecipeLineCommandResult success(boolean result) {
        return new RetypeRecipeLineCommandResult("success", result, null);
    }

    public static RetypeRecipeLineCommandResult fail(String message) {
        return new RetypeRecipeLineCommandResult("fail", null, message);
    }

}
