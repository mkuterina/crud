package com.easydiet.api.rest.entity_attribute;

import com.easydiet.domain.entity_attribute.EntityAttributeValue;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetEntityAttributeValueResponse {
    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private EntityAttributeValue result;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    public static GetEntityAttributeValueResponse success(EntityAttributeValue entityAttributeValue) {
        return new GetEntityAttributeValueResponse(
                "success",
                entityAttributeValue,
                null
        );
    }

    public static GetEntityAttributeValueResponse fail(Exception e) {
        return new GetEntityAttributeValueResponse(
                "fail",
                null,
                e.getMessage()
        );
    }
}
