package com.easydiet.api.rest.group_entry;


import com.easydiet.api.rest.ingredient_entry.RenameIngredientEntryCommandResult;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RenameGroupEntryCommandResult {
    private String status;
    private Boolean result;
    private String message;

    public RenameGroupEntryCommandResult (String status, Boolean result, String message) {
        this.status = status;
        this.result = result;
        this.message = message;
    }
    public static RenameGroupEntryCommandResult  success(boolean result) {
        return new RenameGroupEntryCommandResult ("success", result, null);
    }

    public static RenameGroupEntryCommandResult  fail(String message) {
        return new RenameGroupEntryCommandResult ("fail", null, message);
    }
}
