package com.easydiet.api.rest.entity_link;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateEntityLinkRequest {
    private String typeCode;
    private String originId;
    private String originTypeCode;
    private String destinationId;
    private String destinationTypeCode;
}
