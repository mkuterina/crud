package com.easydiet.api.rest.entity_link;

import com.easydiet.domain.entity_link.EntityLink;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateEntityLinkResponse {
    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private EntityLink result;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    public static CreateEntityLinkResponse success(EntityLink entityLink) {
        return new CreateEntityLinkResponse(
                "success",
                entityLink,
                null
        );
    }

    public static CreateEntityLinkResponse fail(Exception e) {
        return new CreateEntityLinkResponse(
                "fail",
                null,
                e.getMessage()
        );
    }
}
