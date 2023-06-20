package com.easydiet.domain.directory;

import java.util.UUID;
public class DirectoryId {
    private final String directory_id;
    private DirectoryId(String directory_id) {
        this.directory_id = directory_id;
    }
    @Override
    public String toString() {
        return "DirectoryId(" + directory_id + ")";
    }
    public String getDirectory_id() {
        return directory_id;
    }
    public static DirectoryId create() {
        return new DirectoryId(UUID.randomUUID().toString());
    }

    public static DirectoryId create(String id) {
        return new DirectoryId(id);
    }



}