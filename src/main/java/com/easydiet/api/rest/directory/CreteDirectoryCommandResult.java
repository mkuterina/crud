package com.easydiet.api.rest.directory;

import com.easydiet.domain.directory.Directory;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreteDirectoryCommandResult {
    private String status;
    private String directoryId;
    private String message;

    public CreteDirectoryCommandResult(String status, String directoryId, String message) {
        this.status = status;
        this.directoryId = directoryId;
        this.message = message;
    }
    public static CreteDirectoryCommandResult success(Directory directory) {
        return new CreteDirectoryCommandResult("success", directory.getDirectoryId(), null);
    }
    public static CreteDirectoryCommandResult fail(String message) {
        return new CreteDirectoryCommandResult("fail", null, message);

    }
}
