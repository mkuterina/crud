package com.easydiet.domain.group_entry;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GroupEntryType {
    private final String type;

    @Override
    public String toString() {
        return "GroupEntryType(" + type + ")";
    }

    public static GroupEntryType create(String type) {
        if (type == null || type.isBlank()) {
            throw new IllegalStateException("Укажите тип группы.");
        }
        return new GroupEntryType(type);
    }
}
