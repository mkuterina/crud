package com.easydiet.api.rest.recipe_line;

import lombok.Data;

@Data
public class CreateRecipeLineCommand {
    private String recipe_entry_id;
    private String directory_id;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
