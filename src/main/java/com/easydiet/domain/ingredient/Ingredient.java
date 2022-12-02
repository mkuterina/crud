package com.easydiet.domain.ingredient;

import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Entity
@Table(name = "ingredient")
public class Ingredient {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "delete_date")
    private LocalDateTime deleteDate;

    @Column(name = "ingredient_name")
    private String name;

    public Ingredient() {
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getDeleteDate() {
        return deleteDate;
    }

    public String getName() {
        return name;
    }

    //
    // IngredientOperations Support
    //

    protected Ingredient(IngredientId id, IngredientName name, LocalDateTime createDate) {
        this.id = id.getId();
        this.name = name.getName();
        this.createDate = createDate;
    }

    //
    // Domain Logic
    //

    public boolean isDeleted() {
        return deleteDate != null;
    }

    public boolean rename(IngredientName newName) {
        if (newName == null) {
            throw new IllegalStateException("Имя ингридиента должно быть задано");
        }

        if (deleteDate != null) {
            return false;
        }
        else if (this.name.equals(newName.getName())) {
            return false;
        }
        else {
                this.name = newName.getName();
                return true;
        }
    }

    public boolean delete() {
        if (deleteDate != null) {
            return false;
        }
        else {
            this.deleteDate = LocalDateTime.now();
            return true;
        }
    }
}
