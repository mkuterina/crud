package com.easydiet.domain.group_entry;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GroupEntryDescriptionConverter implements AttributeConverter<GroupEntryDescription, String> {
    @Override
    public String convertToDatabaseColumn(GroupEntryDescription description) {
        return description.getDescription();
    }

    @Override
    public GroupEntryDescription convertToEntityAttribute(String description) {
        return GroupEntryDescription.create(description);
    }
}
