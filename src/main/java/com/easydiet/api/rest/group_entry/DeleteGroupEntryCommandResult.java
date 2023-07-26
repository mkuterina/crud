package com.easydiet.api.rest.group_entry;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeleteGroupEntryCommandResult {
    private String status;
    private Boolean result;
    private String message;

    public DeleteGroupEntryCommandResult(String status, Boolean result, String message) {
        this.status = status;
        this.result = result;
        this.message = message;
    }
    public static DeleteGroupEntryCommandResult success(boolean result) {
        return new DeleteGroupEntryCommandResult("success", result, null);
    }
    public static DeleteGroupEntryCommandResult fail(String message) {
        return new DeleteGroupEntryCommandResult("fail", null, message);
    }
}
