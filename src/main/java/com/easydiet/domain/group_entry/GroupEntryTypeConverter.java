package com.easydiet.domain.group_entry;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GroupEntryTypeConverter implements AttributeConverter<GroupEntryType, String> {
    @Override
    public String convertToDatabaseColumn(GroupEntryType type) {
        return type.getType();
    }

    @Override
    public GroupEntryType convertToEntityAttribute(String type) {
        return GroupEntryType.create(type);
    }
}
