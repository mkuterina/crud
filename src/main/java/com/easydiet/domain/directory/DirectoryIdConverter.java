package com.easydiet.domain.directory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class DirectoryIdConverter implements
        AttributeConverter<DirectoryId, String> {
    @Override
    public String convertToDatabaseColumn(DirectoryId directoryId) {
        return directoryId.getDirectoryId();
    }

    @Override
    public DirectoryId convertToEntityAttribute(String id) {
        return DirectoryId.create(id);
    }
}
