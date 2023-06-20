package com.easydiet.api.rest.recipe_entry;

import lombok.Data;

@Data
public class CreateRecipeEntryCommand {
    private String directory_id;
    private String name;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
