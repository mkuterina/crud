package com.easydiet.api.rest.directory;

import com.easydiet.domain.directory.Directory;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreteDirectoryCommandResult {
    private String status;
    private String directory_id;
    private String message;

    public CreteDirectoryCommandResult(String status, String directory_id, String message) {
        this.status = status;
        this.directory_id = directory_id;
        this.message = message;
    }
    public static CreteDirectoryCommandResult success(Directory directory) {
        return new CreteDirectoryCommandResult("success", directory.getDirectory_id(), null);
    }
    public static CreteDirectoryCommandResult fail(String message) {
        return new CreteDirectoryCommandResult("fail", null, message);

    }
}
