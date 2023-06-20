package com.easydiet.domain.recipe_entry;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
@Converter
public class RecipeEntryNameConverter implements
        AttributeConverter<RecipeEntryName, String> {
    @Override
    public String convertToDatabaseColumn(RecipeEntryName name) {
        return name.getName();
    }
    @Override
    public RecipeEntryName convertToEntityAttribute(String name) {
        return RecipeEntryName.create(name);
    }
}
