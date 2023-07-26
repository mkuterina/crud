package com.easydiet.api.rest.group_entry;

import com.easydiet.domain.group_entry.GroupEntry;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetGroupEntryResponse {
    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<GroupEntry> result;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    public static GetGroupEntryResponse success(List<GroupEntry> groupEntries) {
        return new GetGroupEntryResponse(
                "success",
                groupEntries,
                null
        );
    }

    public static GetGroupEntryResponse fail(Exception e) {
        return new GetGroupEntryResponse(
                "fail",
                null,
                e.getMessage()
        );
    }
}
