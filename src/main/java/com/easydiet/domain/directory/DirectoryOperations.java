package com.easydiet.domain.directory;

import java.time.LocalDateTime;

public class DirectoryOperations {
    public static Directory create(DirectoryName name,
                                   DirectoryType type,
                                   DirectoryDescription description,
                                   String workspaceId) {
        if (name == null) {
            throw new IllegalStateException("Имя справочника должно быть задано.");
        }
        return new Directory(DirectoryId.create(),
                name,
                type,
                description,
                LocalDateTime.now(),
                workspaceId);
    }
}
