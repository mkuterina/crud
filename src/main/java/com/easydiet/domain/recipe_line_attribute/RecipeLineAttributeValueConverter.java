package com.easydiet.domain.recipe_line_attribute;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RecipeLineAttributeValueConverter implements
        AttributeConverter<RecipeLineAttributeValue, String> {
    @Override
    public String convertToDatabaseColumn(RecipeLineAttributeValue value) {
        return value.getValue();
    }
    @Override
    public RecipeLineAttributeValue convertToEntityAttribute(String value) {
        return RecipeLineAttributeValue.create(value);
    }
}
