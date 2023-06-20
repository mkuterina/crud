package com.easydiet.domain.ingredient_entry;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
@Converter
public class IngredientEntryNameConverter implements
        AttributeConverter<IngredientEntryName, String> {
    @Override
    public String convertToDatabaseColumn(IngredientEntryName name) {
        return name.getName();
    }
    @Override
    public IngredientEntryName convertToEntityAttribute(String name) {
        return IngredientEntryName.create(name);
    }
}
