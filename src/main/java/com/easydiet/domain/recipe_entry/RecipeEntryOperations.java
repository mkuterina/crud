package com.easydiet.domain.recipe_entry;

import com.easydiet.domain.EntityStatus;
import com.easydiet.domain.directory.DirectoryId;
import java.time.LocalDateTime;

public class RecipeEntryOperations {
    public static RecipeEntry create(
            DirectoryId directoryId,
            RecipeEntryName name,
            RecipeEntryContent content) {
        if (name == null) {
            throw new IllegalStateException("Название рецепта должно быть задано.");
        }
        return new RecipeEntry(
                        directoryId,
                        RecipeEntryId.create(),
                LocalDateTime.now(),
                name,
                content.getContent(),
                EntityStatus.ENABLED
        );
    }
}
