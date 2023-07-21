package com.easydiet.service.group_entry;

public class GroupEntryNotFoundException extends Exception {
    public GroupEntryNotFoundException (String id) {
        super("Группа " + id + " не найдена.");
    }
}
