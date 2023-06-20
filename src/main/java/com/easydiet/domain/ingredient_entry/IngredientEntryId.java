package com.easydiet.domain.ingredient_entry;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.UUID;

public class IngredientEntryId implements Serializable {
    @Column(name = "id")
    private String id;

    private IngredientEntryId() {
        this.id = "DEFAULT";
    }

    private IngredientEntryId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "IngredientEntryId(" + id + ")";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static IngredientEntryId create() {
        return new IngredientEntryId(UUID.randomUUID().toString());
    }

    public static IngredientEntryId create(String s) {
        if (s == null || s.isBlank()) {
            throw new IllegalStateException("Идентификатор ингридиента не может быть пустым");
        }
        return new IngredientEntryId(s);
    }
}
