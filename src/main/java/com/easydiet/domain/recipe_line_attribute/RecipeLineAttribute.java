package com.easydiet.domain.recipe_line_attribute;

import com.easydiet.domain.EntityStatus;
import com.easydiet.domain.EntityStatusConverter;
import com.easydiet.domain.directory.DirectoryId;
import com.easydiet.domain.recipe_entry.RecipeEntryId;
import com.easydiet.domain.recipe_line.RecipeLineId;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recipe_line_attribute")
public class RecipeLineAttribute {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "recipe_line_id")
    private String recipe_line_id;

    @Column(name = "name")
    @Convert(converter = RecipeLineAttributeNameConverter.class)
    private RecipeLineAttributeName name;

    @Column(name = "value")
    @Convert(converter = RecipeLineAttributeValueConverter.class)
    private RecipeLineAttributeValue value;

    @Column(name = "recipe_entry_id")
    private String recipe_entry_id;

    @Column(name = "directory_id")
    private String directory_id;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "delete_date")
    private LocalDateTime deleteDate;

    @Column(name = "recipe_status")
    @Convert(converter = EntityStatusConverter.class)
    private EntityStatus status;

    public RecipeLineAttribute() {
    }

    public RecipeLineAttributeId getId() {
        return RecipeLineAttributeId.create(id);
    }

    public String getRecipe_line_id() {
        return recipe_line_id;
    }

    public RecipeLineAttributeName getName() {
        return name;
    }

    public RecipeLineAttributeValue getValue() {
        return value;
    }

    public String getRecipe_entry_id() {
        return recipe_entry_id;
    }

    public String getDirectory_id() {
        return directory_id;
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
    // IngredientOperations Support
    //


    public RecipeLineAttribute(RecipeLineAttributeId id,
                               RecipeLineId recipe_line_id,
                               RecipeLineAttributeName name,
                               RecipeLineAttributeValue value,
                               RecipeEntryId recipe_entry_id,
                               DirectoryId directory_id,
                               LocalDateTime createDate,
                               EntityStatus status) {
        this.id = id.getId();
        this.recipe_line_id = recipe_line_id.getId();
        this.name = name;
        this.value = value;
        this.recipe_entry_id = recipe_entry_id.getId();
        this.directory_id = directory_id.getDirectory_id();
        this.createDate = createDate;
        this.status = status.ENABLED;
    }

    //
    // Domain Logic
    //


}
