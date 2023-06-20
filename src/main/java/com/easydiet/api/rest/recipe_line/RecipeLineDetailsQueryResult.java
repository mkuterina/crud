package com.easydiet.api.rest.recipe_line;

import com.easydiet.domain.recipe_line.RecipeLine;
import com.easydiet.domain.recipe_line.RecipeLineId;
import com.easydiet.domain.recipe_line.RecipeLineType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipeLineDetailsQueryResult {
    private String status;
    private RecipeLineId id;
    private String recipe_entry_id;
    private String directory_id;
    private RecipeLineType type;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deleteDate;
    private String message;

    public RecipeLineDetailsQueryResult(String status, RecipeLine recipeLine, String message) {
        this.status = status;
        this.message = message;
        if (recipeLine != null) {
            this.id = recipeLine.getId();
            this.recipe_entry_id = recipeLine.getRecipe_entry_id();
            this.directory_id = recipeLine.getDirectory_id();
            this.type = recipeLine.getType();
            this.createDate = recipeLine.getCreateDate();
            this.deleteDate = recipeLine.getDeleteDate();
        }
    }
    public static RecipeLineDetailsQueryResult success(RecipeLine recipeLine) {
        return new RecipeLineDetailsQueryResult("success", recipeLine, null);
    }

    public static RecipeLineDetailsQueryResult fail(String message) {
        return new RecipeLineDetailsQueryResult("fail", null, message);
    }
}
