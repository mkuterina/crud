package com.easydiet.domain.directory;

public class DirectoryType {
    private final String type;

    private DirectoryType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DirectoryType(" + type + ")";
    }

    public String getType() {
        return type;
    }
    public static DirectoryType create(String type) {
        if (type == null || type.isBlank()) {
            throw new IllegalStateException("Укажите тип справочника");
        }
        return new DirectoryType(type);
    }
}

