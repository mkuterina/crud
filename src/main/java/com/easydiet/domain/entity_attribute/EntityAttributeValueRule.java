package com.easydiet.domain.entity_attribute;


import org.hibernate.type.EntityType;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class EntityAttributeValueRule {
    @ManyToOne
    @JoinColumn(name = "entity_type_code", referencedColumnName = "code")
    private EntityType entityType;

    @ManyToOne
    @JoinColumn(name = "entity_type_attribute_id", referencedColumnName = "entity_type_attribute.id")
    private EntityAttributeValue entityTypeAttributeId;

    public boolean allows(EntityType entityType, String entityTypeAttributeId) {
        return (this.entityType.equals(entityType) && entityTypeAttributeId.equals(entityTypeAttributeId));
    }
}
