package com.easydiet.api.rest.ingredient_entry;

import lombok.Data;

@Data
public class CreateIngredientEntryCommand {
    private String directory_id;
    private String name;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
