package com.easydiet.domain.group_entry;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GroupEntryName {
    private static final int MAX_LENGTH = 200;
    private final String name;

    @Override
    public String toString() {
        return "GroupEntryName(" + name + ")";
    }

    public static GroupEntryName create(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalStateException("Имя группы не может быть пустым.");
        }
        if (name.length() > MAX_LENGTH) {
            throw new IllegalStateException("Имя группы не может быть длиннее " + MAX_LENGTH + " символов.");
        }
        return new GroupEntryName(name);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (!(other instanceof GroupEntryName otherName)) {
            return false;
        }
        return this.name.equals(otherName.name);
    }
}
