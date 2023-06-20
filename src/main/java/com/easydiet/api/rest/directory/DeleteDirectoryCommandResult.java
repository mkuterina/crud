package com.easydiet.api.rest.directory;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeleteDirectoryCommandResult {
    private String status;
    private Boolean result;
    private String message;

    public DeleteDirectoryCommandResult(String status, Boolean result, String message) {
        this.status = status;
        this.result = result;
        this.message = message;
    }
    public static DeleteDirectoryCommandResult success(boolean result) {
        return new DeleteDirectoryCommandResult("success", result, null);
        }
    public static DeleteDirectoryCommandResult fail(String message) {
        return new DeleteDirectoryCommandResult("fail", null, message);
    }
}
