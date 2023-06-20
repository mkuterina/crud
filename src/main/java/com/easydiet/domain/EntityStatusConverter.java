package com.easydiet.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class EntityStatusConverter implements AttributeConverter<EntityStatus, String> {
    @Override
    public String convertToDatabaseColumn(EntityStatus entityStatus) {
        return entityStatus.name();
    }

    @Override
    public EntityStatus convertToEntityAttribute(String entityStatusName) {
        for (EntityStatus entityStatus : EntityStatus.values()) {
            if (entityStatus.name().equals(entityStatusName)) {
                return entityStatus;
            }
        }
        return EntityStatus.UNKNOWN;
    }
}
