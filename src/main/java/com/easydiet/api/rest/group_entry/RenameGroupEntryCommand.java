package com.easydiet.api.rest.group_entry;

import lombok.Data;

@Data
public class RenameGroupEntryCommand {
    private String newName;
    private String newType;
    private String newDescription;
}
