package com.easydiet.domain.ingredient;

import java.util.UUID;

public class IngredientId {
    private final String id;

    private IngredientId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "IngredientId(" + id + ")";
    }

    public String getId() {
        return id;
    }

    public static IngredientId create() {
        return new IngredientId(UUID.randomUUID().toString());
    }
}
