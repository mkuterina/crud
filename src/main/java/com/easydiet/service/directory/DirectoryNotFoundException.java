package com.easydiet.service.directory;

public class DirectoryNotFoundException extends Exception {
    public DirectoryNotFoundException(String directoryId) {
        super("Справочник " + directoryId + " не найден.");
    }
}
