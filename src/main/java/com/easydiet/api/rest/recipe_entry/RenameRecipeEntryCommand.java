package com.easydiet.api.rest.recipe_entry;

import lombok.Data;

@Data
public class RenameRecipeEntryCommand {
    private String newName;
    private String newContent;

}
