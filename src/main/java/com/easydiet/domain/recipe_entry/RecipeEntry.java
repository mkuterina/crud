package com.easydiet.domain.recipe_entry;

import com.easydiet.domain.EntityStatus;
import com.easydiet.domain.EntityStatusConverter;
import com.easydiet.domain.directory.DirectoryId;

import java.lang.String;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recipe_entry")
public class RecipeEntry {

    @Column(name = "directory_id")
    private String directoryId;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "delete_date")
    private LocalDateTime deleteDate;

    @Column(name = "recipe_name")
    @Convert(converter = RecipeEntryNameConverter.class)
    private RecipeEntryName name;

    @Column(name = "recipe_content")
    @Convert(converter = RecipeEntryContentConverter.class)
    private RecipeEntryContent content;

    @Column(name = "recipe_status")
    @Convert(converter = EntityStatusConverter.class)
    private EntityStatus status;

    public RecipeEntry() {
    }


    public String getDirectoryId() {
        return directoryId;
    }

    public RecipeEntryId getId() {
        return RecipeEntryId.create(id);
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getDeleteDate() {
        return deleteDate;
    }

    public RecipeEntryName getName() {
        return name;
    }

    public RecipeEntryContent getContent() {
        return content;
    }

    public EntityStatus getStatus() {
        return status;
    }

    //
    // IngredientOperations Support
    //

    public RecipeEntry(DirectoryId directoryId,
                       RecipeEntryId id,
                       LocalDateTime createDate,
                       RecipeEntryName name,
                       String content, EntityStatus status) {
        this.directoryId = directoryId.getDirectoryId();
        this.id = id.getId();
        this.createDate = createDate;
        this.name = name;
        this.content = RecipeEntryContent.create(content);
        this.status = EntityStatus.ENABLED;
    }

    //
    // Domain Logic
    //
    public boolean isDeleted() {
        return deleteDate != null;
    }

    public boolean rename(RecipeEntryName newName, RecipeEntryContent content) {
        if (newName == null) {
            throw new IllegalStateException("Имя рецепта должно быть задано.");
        }
        if (content == null) {
            throw new IllegalStateException("Описание рецепта должно быть задано.");
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
        } else {
            this.deleteDate = LocalDateTime.now();
            return true;
        }
    }
}

