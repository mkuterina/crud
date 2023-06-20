package com.easydiet.domain.recipe_entry;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RecipeEntryContentConverter implements AttributeConverter<RecipeEntryContent, String> {
    @Override
    public String convertToDatabaseColumn(RecipeEntryContent content) {
        return content.getContent();
    }
    @Override
    public RecipeEntryContent convertToEntityAttribute(String content) {
        return RecipeEntryContent.create(content);
    }
}

