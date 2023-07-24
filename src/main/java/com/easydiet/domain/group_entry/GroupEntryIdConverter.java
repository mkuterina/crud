package com.easydiet.domain.group_entry;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GroupEntryIdConverter implements AttributeConverter<GroupEntryId, String> {

    @Override
    public String convertToDatabaseColumn(GroupEntryId id) {
        return id.getId();
    }

    @Override
    public GroupEntryId convertToEntityAttribute(String id) {
        return GroupEntryId.create(id);
    }
}
