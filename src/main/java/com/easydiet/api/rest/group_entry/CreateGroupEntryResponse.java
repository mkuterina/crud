package com.easydiet.api.rest.group_entry;

import com.easydiet.domain.group_entry.GroupEntry;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateGroupEntryResponse {
    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GroupEntry result;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    public static CreateGroupEntryResponse success(GroupEntry groupEntry) {
        return new CreateGroupEntryResponse(
                "success",
                groupEntry,
                null
        );
    }

    public static CreateGroupEntryResponse fail(Exception e) {
        return new CreateGroupEntryResponse(
                "fail",
                null,
                e.getMessage()
        );
    }

}
