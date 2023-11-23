package com.easydiet.api.rest.entity_attribute;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeleteEntityAttributeValueCommandResult {
    private String status;
    private Boolean result;
    private String message;

    public DeleteEntityAttributeValueCommandResult(String status, Boolean result, String message) {
        this.status = status;
        this.result = result;
        this.message = message;
    }
    public static DeleteEntityAttributeValueCommandResult success(boolean result) {
        return new DeleteEntityAttributeValueCommandResult("success", result, null);
    }
    public static DeleteEntityAttributeValueCommandResult fail(String message) {
        return new DeleteEntityAttributeValueCommandResult("fail", null, message);
    }
}
