package com.easydiet.domain.recipe_line;

import com.easydiet.domain.EntityStatus;
import com.easydiet.domain.EntityStatusConverter;
import com.easydiet.domain.directory.DirectoryId;
import com.easydiet.domain.recipe_entry.RecipeEntryId;
import lombok.ToString;


import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Entity
@Table(name = "recipe_line")
public class RecipeLine {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "recipe_entry_id")
    private String recipe_entry_id;

    @Column(name = "directory_id")
    private String directory_id;

    @Column(name = "recipe_line_type")
    @Convert(converter = RecipeLineTypeConverter.class)
    private RecipeLineType type;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "delete_date")
    private LocalDateTime deleteDate;

    @Column(name = "status")
    @Convert(converter = EntityStatusConverter.class)
    private EntityStatus status;

    public RecipeLine() {

    }

    public RecipeLine(RecipeLineId id,
                      RecipeEntryId recipe_entry_id,
                      DirectoryId directory_id,
                      RecipeLineType type,
                      LocalDateTime createDate,
                      EntityStatus status) {
        this.id = id.getId();
        this.recipe_entry_id = recipe_entry_id.getId();
        this.directory_id = directory_id.getDirectory_id();
        this.type = type;
        this.createDate = createDate;
        this.status = EntityStatus.ENABLED;
    }

    public RecipeLineId getId() {
        return RecipeLineId.create(id);
    }

    public String getRecipe_entry_id() {
        return recipe_entry_id;
    }

    public String getDirectory_id() {
        return directory_id;
    }

    public RecipeLineType getType() {
        return type;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getDeleteDate() {
        return deleteDate;
    }

    public EntityStatus getStatus() {
        return status;
    }

    //
    // RecipeLineOperationsSupport
    //

    public static RecipeLine create(
            RecipeEntryId recipe_entry_id,
            DirectoryId directoryId,
            RecipeLineType type) {

        if (type == null) {
            throw new IllegalStateException("Тип связи должен быть задан.");
        }

        return new RecipeLine(RecipeLineId.create(),
                recipe_entry_id,
                directoryId,
                type,
                LocalDateTime.now(),
                EntityStatus.ENABLED
        );
    }

    //
    // Domain Logic
    //

    public boolean isDeleted() {
        return deleteDate == null;
    }

    public boolean retype(RecipeLineType newType) {
        if (newType == null) {
            throw new IllegalStateException("Тип связи рецепта должен быть задан.");
        }

        if (deleteDate != null) {
            return false;
        }
        else if (this.type.equals(newType)) {
            return false;
        }
        else {
            this.type = newType;
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
