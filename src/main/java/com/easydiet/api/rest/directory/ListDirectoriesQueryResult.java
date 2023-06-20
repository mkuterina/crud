package com.easydiet.api.rest.directory;

import com.easydiet.domain.directory.Directory;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListDirectoriesQueryResult {
    private String status;
    private List<DirectoryBrief> directories;
    private String message;

    public ListDirectoriesQueryResult(String status, List<DirectoryBrief> directories, String message) {
        this.status = status;
        this.directories = directories;
        this.message = message;
    }
    public static ListDirectoriesQueryResult success(List<Directory> directoryList) {
        List<DirectoryBrief> directories = directoryList.stream().map(DirectoryBrief::from).toList();
        return new ListDirectoriesQueryResult("success",directories, null);
    }
    public static ListDirectoriesQueryResult fail(String message) {
        return new ListDirectoriesQueryResult("fail", null, message);
    }
}
