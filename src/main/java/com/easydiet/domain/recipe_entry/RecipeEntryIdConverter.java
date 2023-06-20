package com.easydiet.domain.recipe_entry;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RecipeEntryIdConverter implements AttributeConverter<RecipeEntryId, String> {
    @Override
    public String convertToDatabaseColumn(RecipeEntryId id) {
        return id.getId();}
    @Override
    public RecipeEntryId convertToEntityAttribute(String id) {
        return RecipeEntryId.create(id);
    }

}
