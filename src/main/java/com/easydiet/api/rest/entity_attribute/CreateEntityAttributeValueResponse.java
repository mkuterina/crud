package com.easydiet.api.rest.entity_attribute;

import com.easydiet.domain.entity_attribute.EntityAttributeValue;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateEntityAttributeValueResponse {
    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private EntityAttributeValue result;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    public static CreateEntityAttributeValueResponse success(EntityAttributeValue entityAttributeValue) {
        return new CreateEntityAttributeValueResponse(
                "success",
                entityAttributeValue,
                null
        );
    }

    public static CreateEntityAttributeValueResponse fail(Exception e) {
        return new CreateEntityAttributeValueResponse(
                "fail",
                null,
                e.getMessage()
        );
    }
}
