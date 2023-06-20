package com.easydiet.domain.ingredient_entry;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class IngredientEntryDescriptionConverter implements AttributeConverter<IngredientEntryDescription, String> {
    @Override
    public String convertToDatabaseColumn(IngredientEntryDescription description) {
        return description.getDescription();
    }

    @Override
    public IngredientEntryDescription convertToEntityAttribute(String description) {
        return IngredientEntryDescription.create(description);
    }
}
