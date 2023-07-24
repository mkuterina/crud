package com.easydiet.domain.group_entry;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GroupEntryDescription {
    private final String description;

    @Override
    public String toString() {
        return "GroupEntryDescription(" + description + ")";
    }

    public static GroupEntryDescription create(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalStateException("Добавьте описание группы");
        }
        return new GroupEntryDescription(description);
    }
}
