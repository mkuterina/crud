package com.easydiet.domain.group_entry;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GroupEntryNameConverter implements AttributeConverter<GroupEntryName, String> {
    @Override
    public String convertToDatabaseColumn(GroupEntryName name) {
        return name.getName();
    }

    @Override
    public GroupEntryName convertToEntityAttribute(String name) {
        return GroupEntryName.create(name);
    }
}
