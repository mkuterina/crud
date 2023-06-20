package com.easydiet.api.rest.ingredient_entry;

import com.easydiet.domain.ingredient_entry.IngredientEntry;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListIngredientEntriesQueryResult {
    private String status;
    private List<IngredientEntryBrief> ingredientEntries;
    private String message;

    ListIngredientEntriesQueryResult(String status, List<IngredientEntryBrief> ingredientEntries, String message) {
        this.status = status;
        this.ingredientEntries = ingredientEntries;
        this.message = message;
    }

    public static ListIngredientEntriesQueryResult success(List<IngredientEntry> ingredientEntriesList) {
        List<IngredientEntryBrief> ingredientEntries = ingredientEntriesList.stream()
                .map(IngredientEntryBrief::from)
                .toList();
        return new ListIngredientEntriesQueryResult("success", ingredientEntries, null);
    }

    public static ListIngredientEntriesQueryResult fail(String message) {
        return new ListIngredientEntriesQueryResult("fail", null, message);
    }
}
