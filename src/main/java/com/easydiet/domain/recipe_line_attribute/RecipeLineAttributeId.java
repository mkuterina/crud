package com.easydiet.domain.recipe_line_attribute;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.UUID;

public class RecipeLineAttributeId extends Throwable implements Serializable {

    @Column(name = "id")
    private String id;

    public RecipeLineAttributeId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RecipeLineAttributeId(" + id + ")";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static RecipeLineAttributeId create() {
        return new RecipeLineAttributeId(UUID.randomUUID().toString());
    }

    public static RecipeLineAttributeId create(String s) {
        if (s == null || s.isBlank()) {
            throw new IllegalStateException("Идентификатор не может быть пустым.");
        }
        return new RecipeLineAttributeId(s);
    }
}
