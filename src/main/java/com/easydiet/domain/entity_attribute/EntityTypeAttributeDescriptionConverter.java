package com.easydiet.domain.entity_attribute;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class EntityTypeAttributeDescriptionConverter implements AttributeConverter<EntityTypeAttributeDescription, String> {

    @Override
    public String convertToDatabaseColumn(EntityTypeAttributeDescription description) {
        return description.getDescription();
    }

    @Override
    public EntityTypeAttributeDescription convertToEntityAttribute(String name) {
        return EntityTypeAttributeDescription.create(name);
    }
}
