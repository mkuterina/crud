package com.easydiet.service.directory;

public class DirectoryNotFoundException extends Exception {
    public DirectoryNotFoundException(String directory_id) {
        super("Справочник " + directory_id + " не найден.");
    }
}
