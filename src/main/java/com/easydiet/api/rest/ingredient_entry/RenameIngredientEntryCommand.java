package com.easydiet.api.rest.ingredient_entry;

import lombok.Data;

@Data
public class RenameIngredientEntryCommand {
    private String newName;
    private String newDescription;
}
