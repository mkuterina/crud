package com.easydiet.api.rest.directory;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class CreateDirectoryCommand {
    private String name;
    private String type;
    private String description;
}

