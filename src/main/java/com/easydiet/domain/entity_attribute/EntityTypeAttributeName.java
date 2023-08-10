package com.easydiet.domain.entity_attribute;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EntityTypeAttributeName {
    private static final int MAX_LENGTH = 50;
    private final String name;

    @Override
    public String toString() {
        return "EntityTypeAttributeName(" + name + ")";
    }
    public static EntityTypeAttributeName create(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalStateException("Имя типа атрибута сущности не может быть пустым.");
        }
        if (name.length() > MAX_LENGTH) {
            throw new IllegalStateException("Имя типа атрибута сущности не может быть длиннее " + MAX_LENGTH + " символов.");
        }
        return new EntityTypeAttributeName(name);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (!(other instanceof EntityTypeAttributeName otherName)) {
            return false;
        }
        return this.name.equals(otherName.name);
    }
}
