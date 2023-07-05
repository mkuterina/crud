package com.easydiet.api.rest.ingredient_entry;

import com.easydiet.domain.ingredient_entry.IngredientEntry;
import com.easydiet.domain.ingredient_entry.IngredientEntryId;
import com.easydiet.domain.ingredient_entry.IngredientEntryName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IngredientEntryDetailsQueryResult {
    private String status;
    private String directoryId;
    private IngredientEntryId id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deleteDate;
    private IngredientEntryName name;
    private String message;

    public IngredientEntryDetailsQueryResult(String status, IngredientEntry ingredientEntry, String message) {
        this.status = status;
        this.message = message;
        if (ingredientEntry != null) {
            this.directoryId = ingredientEntry.getDirectoryId();
            this.id = ingredientEntry.getId();
            this.createDate = ingredientEntry.getCreateDate();
            this.deleteDate = ingredientEntry.getDeleteDate();
            this.name = ingredientEntry.getName();
        }
    }

    public static IngredientEntryDetailsQueryResult success(IngredientEntry ingredientEntry) {
        return new IngredientEntryDetailsQueryResult("success", ingredientEntry, null);
    }

    public static IngredientEntryDetailsQueryResult fail(String message) {
        return new IngredientEntryDetailsQueryResult("fail", null, message);
    }
}
