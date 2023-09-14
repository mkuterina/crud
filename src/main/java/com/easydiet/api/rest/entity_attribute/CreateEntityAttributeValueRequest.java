package com.easydiet.api.rest.entity_attribute;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateEntityAttributeValueRequest {
    private String entityTypeCode;
    private String entityId;
    private String attributeName;
    private String value;
}
