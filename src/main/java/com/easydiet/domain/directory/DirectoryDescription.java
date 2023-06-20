package com.easydiet.domain.directory;

public class DirectoryDescription {
    private final String description;
    private DirectoryDescription(String description) {
        this.description = description;
    }
    public String toString() {
        return "DirectoryDescription(" + description + ")";
    }
    public String getDescription() {
        return description;
    }
    public static DirectoryDescription create(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalStateException("Добавьте описание справочника");
        }
        return new DirectoryDescription(description);
    }
}