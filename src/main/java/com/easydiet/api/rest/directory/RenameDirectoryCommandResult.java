package com.easydiet.api.rest.directory;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RenameDirectoryCommandResult {
    private String status;
    private Boolean result;
    private String message;

    public RenameDirectoryCommandResult(String status, Boolean result, String message) {
        this.status = status;
        this.result = result;
        this.message = message;
    }
    public static RenameDirectoryCommandResult success(boolean result) {
        return new RenameDirectoryCommandResult("success", result, null);
    }
    public static RenameDirectoryCommandResult fail(String message) {
        return new RenameDirectoryCommandResult("fail", null, message);
    }
}
