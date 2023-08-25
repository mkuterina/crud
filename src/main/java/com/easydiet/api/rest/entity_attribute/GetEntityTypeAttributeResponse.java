package com.easydiet.api.rest.entity_attribute;

import com.easydiet.domain.entity_attribute.EntityTypeAttribute;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetEntityTypeAttributeResponse {
    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private EntityTypeAttribute result;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    public static GetEntityTypeAttributeResponse success(EntityTypeAttribute entityTypeAttribute) {
        return new GetEntityTypeAttributeResponse(
                "success",
                entityTypeAttribute,
                null
        );
    }

    public static GetEntityTypeAttributeResponse fail(Exception e) {
        return new GetEntityTypeAttributeResponse(
                "fail",
                null,
                e.getMessage()
        );
    }
}
