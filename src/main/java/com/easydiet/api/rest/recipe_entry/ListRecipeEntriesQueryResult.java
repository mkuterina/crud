package com.easydiet.api.rest.recipe_entry;

import com.easydiet.domain.recipe_entry.RecipeEntry;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListRecipeEntriesQueryResult {
    private String status;
    private List<RecipeEntryBrief> recipeEntries;
    private String message;

    public ListRecipeEntriesQueryResult(String status, List<RecipeEntryBrief> recipeEntries, String message) {
        this.status = status;
        this.recipeEntries = recipeEntries;
        this.message = message;
    }

    public static ListRecipeEntriesQueryResult success(List<RecipeEntry> recipeEntriesList) {
        List<RecipeEntryBrief> recipeEntries = recipeEntriesList.stream().map(RecipeEntryBrief::from)
                .toList();
        return new ListRecipeEntriesQueryResult("success", recipeEntries, null);
    }

    public static ListRecipeEntriesQueryResult fail(String message) {
        return new ListRecipeEntriesQueryResult("fail", null, message);
    }

}
