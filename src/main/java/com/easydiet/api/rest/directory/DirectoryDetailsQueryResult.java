package com.easydiet.api.rest.directory;

import com.easydiet.domain.directory.Directory;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DirectoryDetailsQueryResult {
    private String status;
    private String directoryId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deleteDate;
    private String name;
    private String type;
    private String description;
    private String message;

    public DirectoryDetailsQueryResult(String status,Directory directory, String message) {
        this.status = status;
        this.message = message;
        if (directory != null) {
            this.directoryId = directory.getDirectoryId();
            this.createDate = directory.getCreateDate();
            this.deleteDate = directory.getDeleteDate();
            this.name = directory.getName();
            this.type = directory.getType();
            this.description = directory.getDescription();
        }
    }
    public static DirectoryDetailsQueryResult success(Directory directory) {
        return new DirectoryDetailsQueryResult("success", directory, null);
    }
    public static DirectoryDetailsQueryResult fail(String message) {
        return  new DirectoryDetailsQueryResult("fail", null, message);
    }
}

