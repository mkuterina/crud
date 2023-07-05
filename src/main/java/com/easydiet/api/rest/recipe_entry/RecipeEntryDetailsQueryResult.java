package com.easydiet.api.rest.recipe_entry;

import com.easydiet.domain.recipe_entry.RecipeEntry;
import com.easydiet.domain.recipe_entry.RecipeEntryId;
import com.easydiet.domain.recipe_entry.RecipeEntryName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipeEntryDetailsQueryResult {
    private String status;
    private String directoryId;
    private RecipeEntryId id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deleteDate;
    private RecipeEntryName name;
    private String message;

    public RecipeEntryDetailsQueryResult(String status, RecipeEntry recipeEntry, String message) {
        this.status = status;
        this.message = message;
        if (recipeEntry != null) {
            this.directoryId = recipeEntry.getDirectoryId();
            this.id = recipeEntry.getId();
            this.createDate = recipeEntry.getCreateDate();
            this.deleteDate = recipeEntry.getDeleteDate();
            this.name = recipeEntry.getName();
        }
    }
    public static RecipeEntryDetailsQueryResult success(RecipeEntry recipeEntry) {
        return new RecipeEntryDetailsQueryResult("success", recipeEntry, null);
    }

    public static RecipeEntryDetailsQueryResult fail(String message) {
        return new RecipeEntryDetailsQueryResult("fail", null, message);
    }
}
