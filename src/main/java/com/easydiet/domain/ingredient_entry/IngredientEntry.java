package com.easydiet.domain.ingredient_entry;

import com.easydiet.domain.EntityStatus;
import com.easydiet.domain.EntityStatusConverter;
import com.easydiet.domain.directory.DirectoryId;
import java.lang.String;

import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
@ToString
@Entity
@Table(name = "ingredient_entry")
public class IngredientEntry {

    @Column(name = "directory_id")
    private String directoryId;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "delete_date")
    private LocalDateTime deleteDate;

    @Column(name = "ingredient_name")
    @Convert(converter = IngredientEntryNameConverter.class)
    private IngredientEntryName name;

    @Column(name = "ingredient_description")
    @Convert(converter = IngredientEntryDescriptionConverter.class)
    private IngredientEntryDescription description;

    @Column(name = "ingredient_status")
    @Convert(converter = EntityStatusConverter.class)
    private EntityStatus status;

    public IngredientEntry() {
    }

    private IngredientEntry(
            DirectoryId directoryId,
            IngredientEntryId id,
            IngredientEntryName name,
            IngredientEntryDescription description,
            LocalDateTime createDate,
            EntityStatus status) {

        this.directoryId = directoryId.getDirectoryId();
        this.id = id.getId();
        this.createDate = createDate;
        this.name = name;
        this.description = description;
        this.status = EntityStatus.ENABLED;
    }

    public static IngredientEntry create(
            DirectoryId directoryId,
            IngredientEntryName name,
            IngredientEntryDescription description) {

        if (name == null) {
            throw new IllegalStateException("Имя ингридиента должно быть задано");
        }

        return new IngredientEntry(
                directoryId,
                IngredientEntryId.create(),
                name,
                description,
                LocalDateTime.now(),
                EntityStatus.ENABLED
        );
    }

    public String getDirectoryId() {
        return directoryId;
    }

    public IngredientEntryId getId() {
        return IngredientEntryId.create(id);
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getDeleteDate() {
        return deleteDate;
    }

    public IngredientEntryName getName() {
        return name;
    }

    public IngredientEntryDescription getDescription() {
        return description;
    }


    //
    // Domain Logic
    //

    public boolean isDeleted() {
        return deleteDate != null;
    }

    public boolean rename(IngredientEntryName newName) {
        if (newName == null) {
            throw new IllegalStateException("Имя ингридиента должно быть задано");
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
