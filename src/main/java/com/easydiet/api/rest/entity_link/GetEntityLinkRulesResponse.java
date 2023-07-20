package com.easydiet.api.rest.entity_link;

import com.easydiet.domain.entity_link.EntityLinkRule;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetEntityLinkRulesResponse {
    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<EntityLinkRule> result;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    public static GetEntityLinkRulesResponse success(List<EntityLinkRule> rules) {
        return new GetEntityLinkRulesResponse(
                "success",
                rules,
                null
        );
    }

    public static GetEntityLinkRulesResponse fail(Exception e) {
        return new GetEntityLinkRulesResponse(
                "fail",
                null,
                e.getMessage()
        );
    }
}
