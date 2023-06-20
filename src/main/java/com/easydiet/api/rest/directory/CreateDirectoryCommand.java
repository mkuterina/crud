package com.easydiet.api.rest.directory;

import lombok.Data;

@Data
public class CreateDirectoryCommand {
    private String name;
    private String type;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

