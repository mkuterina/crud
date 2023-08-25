package com.easydiet.api.rest.entity_attribute;

import com.easydiet.domain.entity_attribute.EntityAttributeValue;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SetEntityAttributeValueResponse {
    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private EntityAttributeValue result;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    public static SetEntityAttributeValueResponse success(EntityAttributeValue entityAttributeValue) {
        return new SetEntityAttributeValueResponse(
                "success",
                entityAttributeValue,
                null
        );
    }

    public static SetEntityAttributeValueResponse fail(Exception e) {
        return new SetEntityAttributeValueResponse(
                "fail",
                null,
                e.getMessage()
        );
    }
}
