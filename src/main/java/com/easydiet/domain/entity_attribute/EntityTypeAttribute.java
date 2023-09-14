package com.easydiet.domain.entity_attribute;

import com.easydiet.domain.EntityStatus;
import com.easydiet.domain.EntityStatusConverter;
import com.easydiet.domain.entity_link.EntityType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "entity_type_attribute")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EntityTypeAttribute {

    @Id
    private String id;

    @Column(name = "attribute_name")
    @Convert(converter = EntityTypeAttributeNameConverter.class)
    private EntityTypeAttributeName name;

    @ManyToOne
    @JoinColumn(name = "entity_type_code", referencedColumnName = "code")
    private EntityType entityType;

    @Column(name = "attribute_type")
    private String attributeType;

    @Column(name = "description")
    @Convert(converter = EntityTypeAttributeDescriptionConverter.class)
    private EntityTypeAttributeDescription description;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "delete_date")
    private LocalDateTime deleteDate;

    @Column(name = "status")
    @Convert(converter = EntityStatusConverter.class)
    private EntityStatus status;

    public static EntityTypeAttribute create(
            EntityTypeAttributeName name,
            EntityType entityType,
            String attributeType,
            EntityTypeAttributeDescription description
    ) {
        String id = UUID.randomUUID().toString();
        LocalDateTime createDate = LocalDateTime.now();

        return new EntityTypeAttribute(
                id,
                name,
                entityType,
                attributeType,
                description,
                createDate,
                null,
                EntityStatus.ENABLED
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

    public boolean rename(EntityTypeAttributeName newName) {
        if (newName == null) {
            throw new IllegalStateException("Имя типа атрибута сущности должно быть задано.");
        }
        if (deleteDate != null) {
            return false;
        } else if (this.name.equals(newName)) {
            return false;
        } else {
            this.name = newName;
            return true;
        }
    }
}
