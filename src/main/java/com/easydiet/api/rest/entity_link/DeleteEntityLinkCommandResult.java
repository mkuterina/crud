package com.easydiet.api.rest.entity_link;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeleteEntityLinkCommandResult {

        private String status;
        private Boolean result;
        private String message;

        public DeleteEntityLinkCommandResult(String status, Boolean result, String message) {
            this.status = status;
            this.result = result;
            this.message = message;
        }
        public static DeleteEntityLinkCommandResult success(boolean result) {
            return new DeleteEntityLinkCommandResult("success", result, null);
        }
        public static DeleteEntityLinkCommandResult fail(String message) {
            return new DeleteEntityLinkCommandResult("fail", null, message);
        }

}
