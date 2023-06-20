package com.easydiet.domain.recipe_line;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.UUID;

public class RecipeLineId implements Serializable {

    @Column(name = "id")
    private String id;

    public RecipeLineId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RecipeLineId{" + "id='" + id + "}";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static RecipeLineId create() {
        return new RecipeLineId(UUID.randomUUID()
                .toString());
    }
    public static RecipeLineId create(String s) {
        if (s == null || s.isBlank()) {
            throw new IllegalStateException("Идентификатор не может быть пустым.");
        }
        return new RecipeLineId(s);
    }


}
