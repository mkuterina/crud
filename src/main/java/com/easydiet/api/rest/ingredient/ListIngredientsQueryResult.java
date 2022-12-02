package com.easydiet.api.rest.ingredient;

import com.easydiet.domain.ingredient.Ingredient;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListIngredientsQueryResult {
    private String status;
    private List<IngredientBrief> ingredients;
    private String message;

    ListIngredientsQueryResult(String status, List<IngredientBrief> ingredients, String message) {
        this.status = status;
        this.ingredients = ingredients;
        this.message = message;
    }

    public static ListIngredientsQueryResult success(List<Ingredient> ingredientsList) {
        List<IngredientBrief> ingredients = ingredientsList.stream()
                .map(IngredientBrief::from)
                .toList();

        return new ListIngredientsQueryResult("success", ingredients, null);
    }

    public static ListIngredientsQueryResult fail(String message) {
        return new ListIngredientsQueryResult("fail", null, message);
    }
}
