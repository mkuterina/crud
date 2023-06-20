package com.easydiet.api.rest.recipe_line;

import com.easydiet.domain.recipe_line.RecipeLine;
import com.easydiet.domain.recipe_line.RecipeLineId;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateRecipeLineCommandResult {
    private String status;
    private RecipeLineId id;
    private String message;

    public CreateRecipeLineCommandResult(String status, RecipeLineId id, String message) {
        this.status = status;
        this.id = id;
        this.message = message;
    }

    public static CreateRecipeLineCommandResult success(RecipeLine recipeLine) {
        return new CreateRecipeLineCommandResult("success", recipeLine.getId(), null);
    }

    public static CreateRecipeLineCommandResult fail(String message) {
        return new CreateRecipeLineCommandResult("fail", null, message);
    }
}
