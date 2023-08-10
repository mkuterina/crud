package com.easydiet.domain.entity_attribute;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EntityTypeAttributeDescription {
    private final String description;

    @Override
    public String toString() {
        return "EntityTypeAttributeDescription(" + description + ")";
    }

    public static EntityTypeAttributeDescription create(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalStateException("Добавьте описание типа атрибута сущности");
        }
        return new EntityTypeAttributeDescription(description);
    }
}
