package com.easydiet.api.rest.group_entry;

import com.easydiet.domain.group_entry.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupEntryDetailsQueryResult {
    private String status;
    private String directoryId;
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deleteDate;
    private GroupEntryName name;
    private GroupEntryType type;
    private GroupEntryDescription description;
    private String message;

    public GroupEntryDetailsQueryResult(String status, GroupEntry groupEntry, String message) {
        this.status = status;
        this.message = message;
        if (groupEntry != null) {
            this.directoryId = groupEntry.getDirectoryId();
            this.id = groupEntry.getId();
            this.createDate = groupEntry.getCreateDate();
            this.deleteDate = groupEntry.getDeleteDate();
            this.name = groupEntry.getName();
            this.type = groupEntry.getType();
            this.description = groupEntry.getDescription();
        }
    }

     public static GroupEntryDetailsQueryResult success(GroupEntry groupEntry) {
        return new GroupEntryDetailsQueryResult("success", groupEntry, null);
    }

    public static GroupEntryDetailsQueryResult fail(String message) {
        return new GroupEntryDetailsQueryResult("fail", null, message);
    }
}
