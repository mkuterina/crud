package com.easydiet.domain.recipe_entry;

import com.easydiet.domain.EntityStatus;
import com.easydiet.domain.EntityStatusConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.lang.String;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recipe_entry")
public class RecipeEntry {

    @Column(name = "directory_id")
    private String directoryId;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "recipe_name")
    @Convert(converter = RecipeEntryNameConverter.class)
    private RecipeEntryName name;

    @Column(name = "recipe_content")
    @Convert(converter = RecipeEntryContentConverter.class)
    private RecipeEntryContent content;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Getter
    @Column(name = "delete_date")
    private LocalDateTime deleteDate;

    @Column(name = "recipe_status")
    @Convert(converter = EntityStatusConverter.class)
    private EntityStatus status;

    @Column(name = "workspace_id")
    private String workspaceId;


    public static RecipeEntry create(
            String directoryId,
            RecipeEntryName name,
            RecipeEntryContent content,
            String workspaceId) {
        if (name == null) {
            throw new IllegalStateException("Название рецепта должно быть задано.");
        }
        return new RecipeEntry(
                directoryId,
                RecipeEntryId.create().getId(),
                name,
                content,
                LocalDateTime.now(),
                null,
                EntityStatus.ENABLED,
                workspaceId
        );
    }

    public boolean delete() {
        if (deleteDate != null) {
            return false;
        } else {
            this.deleteDate = LocalDateTime.now();
            return true;
        }
    }

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
}
