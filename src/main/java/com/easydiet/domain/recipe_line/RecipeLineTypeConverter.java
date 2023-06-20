package com.easydiet.domain.recipe_line;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RecipeLineTypeConverter implements AttributeConverter<RecipeLineType, String> {

    @Override
    public String convertToDatabaseColumn(RecipeLineType type) {
        return type.getType();
    }

    @Override
    public RecipeLineType convertToEntityAttribute(String type) {
        return RecipeLineType.create(type);
    }

}
