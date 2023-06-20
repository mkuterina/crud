package com.easydiet.domain.recipe_line;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RecipeLineIdConverter implements AttributeConverter<RecipeLineId, String> {
    @Override
    public String convertToDatabaseColumn(RecipeLineId id) {
        return id.getId();
    }

    @Override
    public RecipeLineId convertToEntityAttribute(String id) {
        return RecipeLineId.create(id);
    }
}
