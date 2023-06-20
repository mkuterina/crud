package com.easydiet.domain.ingredient_entry;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class IngredientEntryIdConverter implements
        AttributeConverter<IngredientEntryId, String> {
    @Override
    public String convertToDatabaseColumn(IngredientEntryId id) {
        return id.getId();
    }

    @Override
    public IngredientEntryId convertToEntityAttribute(String id) {
        return IngredientEntryId.create(id);
    }
}
