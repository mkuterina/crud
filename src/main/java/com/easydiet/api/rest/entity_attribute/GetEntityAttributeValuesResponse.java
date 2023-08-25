package com.easydiet.api.rest.entity_attribute;

import com.easydiet.domain.entity_attribute.EntityAttributeValue;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class GetEntityAttributeValuesResponse {
    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<EntityAttributeValue> result;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    public static GetEntityAttributeValuesResponse success(List<EntityAttributeValue> entityAttributeValues) {
        return new GetEntityAttributeValuesResponse(
                "success",
                entityAttributeValues,
                null
        );
    }

    public static GetEntityAttributeValuesResponse fail(Exception e) {
        return new GetEntityAttributeValuesResponse(
                "fail",
                null,
                e.getMessage()
        );
    }
}
