package com.easydiet.domain.recipe_entry;

import lombok.Getter;

@Getter
public class RecipeEntryContent {
    private final String content;
    private RecipeEntryContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "RecipeEntryContent(" + content + ")";
    }

    public static RecipeEntryContent create(String content) {
        if (content == null || content.isBlank()) {
            throw new IllegalStateException("Добавьте описание рецепта.");
        }
        return new RecipeEntryContent(content);
    }
}
