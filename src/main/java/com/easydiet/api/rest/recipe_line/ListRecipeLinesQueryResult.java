package com.easydiet.api.rest.recipe_line;

import com.easydiet.domain.recipe_line.RecipeLine;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListRecipeLinesQueryResult {
    private String status;
    private List<RecipeLineBrief> recipeLines;
    private String message;

    public ListRecipeLinesQueryResult(String status, List<RecipeLineBrief> recipeLines, String message) {
        this.status = status;
        this.recipeLines = recipeLines;
        this.message = message;
    }

    public static ListRecipeLinesQueryResult success(List<RecipeLine> recipeLinesList) {
        List<RecipeLineBrief> recipeLines = recipeLinesList.stream().map(RecipeLineBrief::from)
                .toList();
        return new ListRecipeLinesQueryResult("success", recipeLines, null);
    }

    public static ListRecipeLinesQueryResult fail(String message) {
        return new ListRecipeLinesQueryResult("fail", null, message);
    }

}
