package com.easydiet.domain.ingredient_entry;

import com.easydiet.domain.EntityStatus;
import com.easydiet.domain.EntityStatusConverter;
import java.lang.String;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ingredient_entry")
public class IngredientEntry {

    @Column(name = "directory_id")
    private String directoryId;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "ingredient_name")
    @Convert(converter = IngredientEntryNameConverter.class)
    private IngredientEntryName name;

    @Column(name = "ingredient_description")
    @Convert(converter = IngredientEntryDescriptionConverter.class)
    private IngredientEntryDescription description;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "delete_date")
    private LocalDateTime deleteDate;

    @Column(name = "ingredient_status")
    @Convert(converter = EntityStatusConverter.class)
    private EntityStatus status;

    @Column(name = "workspace_id")
    private String workspaceId;


    public static IngredientEntry create(
            String directoryId,
            IngredientEntryName name,
            IngredientEntryDescription description,
            String workspaceId) {

        if (name == null) {
            throw new IllegalStateException("Имя ингридиента должно быть задано");
        }

        return new IngredientEntry(
                directoryId,
                IngredientEntryId.create().getId(),
                name,
                description,
                LocalDateTime.now(),
                null,
                EntityStatus.ENABLED,
                workspaceId
        );
    }

    //
    // Domain Logic
    //

    public boolean delete() {
        if (deleteDate != null) {
            return false;
        }
        else {
            this.deleteDate = LocalDateTime.now();
            return true;
        }
    }
    public boolean isDeleted() {
        return deleteDate != null;
    }

    public boolean rename(IngredientEntryName newName, 
                          IngredientEntryDescription description) {
        if (newName == null) {
            throw new IllegalStateException("Имя ингридиента должно быть задано");
        }
        if (description == null) {
            throw new IllegalStateException("Описание ингредиента должно быть задано.");
        }

        if (deleteDate != null) {
            return false;
        }
        else if (this.name.equals(newName)) {
            return false;
        }
        else {
            this.name = newName;
            return true;
        }
    }
}
