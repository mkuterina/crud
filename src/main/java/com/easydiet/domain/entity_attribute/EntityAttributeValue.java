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
@Table(name = "entity_attribute_value")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EntityAttributeValue {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "entity_type_code", referencedColumnName = "code")
    private EntityType entityType;

    @Column(name = "entity_id")
    private String entityId;

    @ManyToOne
    @JoinColumn(name = "entity_type_attribute_id", referencedColumnName = "id")
    private EntityTypeAttribute entityTypeAttributeId;

    @Column(name = "value")
    private String value;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "delete_date")
    private LocalDateTime deleteDate;

    @Column(name = "status")
    @Convert(converter = EntityStatusConverter.class)
    private EntityStatus status;

    @Column(name = "workspace_id")
    private String workspaceId;

    public static EntityAttributeValue create(
            EntityType entityType,
            String entityId,
            EntityTypeAttribute entityTypeAttributeId,
            String value,
            String workspaceId
    ) {
        String id = UUID.randomUUID().toString();
        LocalDateTime createDate = LocalDateTime.now();

        return new EntityAttributeValue(
                id,
                entityType,
                entityId,
                entityTypeAttributeId,
                value,
                createDate,
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
}
