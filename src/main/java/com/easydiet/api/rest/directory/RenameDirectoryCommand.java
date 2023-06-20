package com.easydiet.api.rest.directory;

import lombok.Data;

@Data
public class RenameDirectoryCommand {
    private String newName;
    private String newType;
    private String newDescription;
}
