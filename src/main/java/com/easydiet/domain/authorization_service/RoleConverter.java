package com.easydiet.domain.authorization_service;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RoleConverter implements AttributeConverter<Role, String> {
    @Override
    public String convertToDatabaseColumn(Role role) {
        return role.name();
    }

    @Override
    public Role convertToEntityAttribute(String roleName) {
        for (Role role : Role.values()) {
            if (role.name().equals(roleName)) {
                return role;
            }
        }
        return Role.UNKNOWN;
    }
}

