package com.easydiet.domain.entity_attribute;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class EntityTypeAttributeNameConverter implements AttributeConverter<EntityTypeAttributeName, String> {
    @Override
    public String convertToDatabaseColumn(EntityTypeAttributeName name) {
        return name.getName();
    }

    @Override
    public EntityTypeAttributeName convertToEntityAttribute(String name) {
        return EntityTypeAttributeName.create(name);
    }
}
