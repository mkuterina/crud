package com.easydiet.api.rest.ingredient;

import com.easydiet.domain.ingredient.Ingredient;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateIngredientCommandResult {
    private String status;
    private String id;
    private String message;

    private CreateIngredientCommandResult(String status, String id, String message) {
        this.status = status;
        this.id = id;
        this.message = message;
    }

    public static CreateIngredientCommandResult success(Ingredient ingredient) {
        return new CreateIngredientCommandResult("success", ingredient.getId(), null);
    }

    public static CreateIngredientCommandResult fail(String message) {
        return new CreateIngredientCommandResult("fail", null, message);
    }
}
