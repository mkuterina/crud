package com.easydiet.domain.ingredient_entry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class IngredientEntryId implements Serializable {

    @Column(name = "id")
    private String id;

    private IngredientEntryId() {
        this.id = "DEFAULT";
    }

    @Override
    public String toString() {
        return "IngredientEntryId(" + id + ")";
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
