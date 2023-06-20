package com.easydiet.domain.directory;

public class DirectoryName {
    private static final int MAX_LENGTH = 50;
    private final String name;
    private DirectoryName(String name) {
        this.name = name;
    }
    public String toString() {
        return "DirectoryName(" + name + ")";
    }
    public String getName() {
        return name;
    }
    public static DirectoryName create(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalStateException("Имя справочника не может быть пустым");
        }
        if (name.length() > MAX_LENGTH) {
            throw new IllegalStateException("Имя справочника не должно быть длиннее " + MAX_LENGTH + " символов");
        }
        return new DirectoryName(name);
    }
}