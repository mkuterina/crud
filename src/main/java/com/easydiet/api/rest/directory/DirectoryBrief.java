package com.easydiet.api.rest.directory;

import com.easydiet.domain.directory.Directory;
import lombok.Data;

@Data
public class DirectoryBrief {
    private String directoryId;
    private String name;
    private String type;
    private String description;

    public DirectoryBrief(String directory_id, String name, String type, String description) {
        this.directoryId = directory_id;
        this.name = name;
        this.type = type;
        this.description = description;
    }
    public static DirectoryBrief from(Directory directory) {
        return new DirectoryBrief(directory.getDirectoryId(),
                directory.getName(),
                directory.getType(),
                directory.getDescription()
        );
    }
}
