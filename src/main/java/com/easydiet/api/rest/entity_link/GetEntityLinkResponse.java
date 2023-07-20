package com.easydiet.api.rest.entity_link;

import com.easydiet.domain.entity_link.EntityLink;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetEntityLinkResponse {

    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<EntityLink> result;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    public static GetEntityLinkResponse success(List<EntityLink> entityLinks) {
        return new GetEntityLinkResponse(
                "success",
                entityLinks,
                null
        );
    }

    public static GetEntityLinkResponse fail(Exception e) {
        return new GetEntityLinkResponse(
                "fail",
                null,
                e.getMessage()
        );
    }
}
