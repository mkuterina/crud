package com.easydiet.domain.recipe_line_attribute;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RecipeLineAttributeNameConverter implements
        AttributeConverter<RecipeLineAttributeName, String> {

    @Override
    public String convertToDatabaseColumn(RecipeLineAttributeName name) {
        return name.getName();
    }

    @Override
    public RecipeLineAttributeName convertToEntityAttribute(String name) {
        return RecipeLineAttributeName.create(name);
    }
}
