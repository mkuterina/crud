package com.easydiet.api.rest.entity_attribute;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SetEntityAttributeValueRequest {
    private String entityType;
    private String entityId;
    private String entityTypeAttributeName;
    private String value;
}
