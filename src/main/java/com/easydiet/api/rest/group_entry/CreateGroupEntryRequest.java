package com.easydiet.api.rest.group_entry;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class   CreateGroupEntryRequest {
    private String directoryId;
    private String name;
    private String type;
    private String description;
}
