package com.easydiet.domain.recipe_entry;

public class RecipeEntryContent {
    private final String content;
    private RecipeEntryContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "RecipeEntryContent(" + content + ")";
    }

    public String getContent() {
        return content;
    }
    public static RecipeEntryContent create(String content) {
        if (content == null || content.isBlank()) {
            throw new IllegalStateException("Добавьте описание рецепта.");
        }
        return new RecipeEntryContent(content);
    }
}
