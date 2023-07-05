package com.easydiet.domain.directory;

import java.util.UUID;
public class DirectoryId {
    private final String directoryId;
    private DirectoryId(String directoryId) {
        this.directoryId = directoryId;
    }
    @Override
    public String toString() {
        return "DirectoryId(" + directoryId + ")";
    }
    public String getDirectoryId() {
        return directoryId;
    }
    public static DirectoryId create() {
        return new DirectoryId(UUID.randomUUID().toString());
    }

    public static DirectoryId create(String id) {
        return new DirectoryId(id);
    }



}