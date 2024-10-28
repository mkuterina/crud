package com.easydiet.domain.recipe_entry;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class RecipeEntryId implements Serializable {

    @Column(name = "id")
    private String id;

     private RecipeEntryId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RecipeEntryId(" + id + ")";
    }

     public static RecipeEntryId create() {
        return new RecipeEntryId(UUID.randomUUID()
                .toString());
    }
    public static RecipeEntryId create(String s) {
        if (s == null || s.isBlank()) {
            throw new IllegalStateException("Идентификатор не может быть пустым.");
        }
        return new RecipeEntryId(s);
    }
}
