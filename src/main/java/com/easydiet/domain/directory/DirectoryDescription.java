package com.easydiet.domain.directory;

import lombok.Getter;

@Getter
public class DirectoryDescription {
    private final String description;
    private DirectoryDescription(String description) {
        this.description = description;
    }
   @Override
   public String toString() {
        return "DirectoryDescription(" + description + ")";
    }
       public static DirectoryDescription create(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalStateException("Добавьте описание справочника");
        }
        return new DirectoryDescription(description);
    }
}