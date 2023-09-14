package com.easydiet.service.entity_attribute;

import com.easydiet.domain.entity_attribute.*;
import com.easydiet.domain.entity_link.EntityType;
import com.easydiet.domain.entity_link.EntityTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EntityAttributeValueService {

    private final EntityTypeRepository entityTypeRepository;
    private final EntityTypeAttributeRepository entityTypeAttributeRepository;
    private final EntityAttributeValueRepository entityAttributeValueRepository;

    public EntityAttributeValue create(String entityTypeCode,
                                                   String entityId,
                                                   EntityTypeAttributeName attributeName,
                                                   String value) {
        Optional<EntityType> optionalEntityType = entityTypeRepository.findByCode(entityTypeCode);
        if (optionalEntityType.isEmpty()) {
            throw new IllegalArgumentException("Неправильно задан тип сущности");
        }
        EntityType entityType = optionalEntityType.get();

        Optional<EntityTypeAttribute> optionalEntityTypeAttribute = entityTypeAttributeRepository.findByNameAndEntityType(attributeName, entityType);
        if (optionalEntityTypeAttribute.isEmpty()) {
            throw new IllegalArgumentException("Неправильно задано имя атрибута");
        }
        EntityTypeAttribute entityTypeAttribute = optionalEntityTypeAttribute.get();

        EntityAttributeValue entityAttributeValue = EntityAttributeValue.create(entityType, entityId, entityTypeAttribute, value);
        entityAttributeValueRepository.save(entityAttributeValue);

        return entityAttributeValue;
    }
    public boolean delete(String id) {
        Optional<EntityAttributeValue> optionalEntityAttributeValue = entityAttributeValueRepository.findByIdentifier(id);
        if (optionalEntityAttributeValue.isEmpty()) {
            return false;
        } else {
            EntityAttributeValue entityAttributeValue = optionalEntityAttributeValue.get();
            boolean result = entityAttributeValue.delete();
            entityAttributeValueRepository.save(entityAttributeValue);
            return result;
        }
    }

    public List<EntityAttributeValue> findAllByEntityId(String entityId) {
       return entityAttributeValueRepository.findAllByEntityId(entityId).stream()
               .filter(EntityAttributeValue -> !EntityAttributeValue.isDeleted())
               .toList();
    }

    public EntityAttributeValue findByIdentifier(String id) {
        Optional<EntityAttributeValue> optionalEntityAttributeValue = entityAttributeValueRepository.findByIdentifier(id);
        if (optionalEntityAttributeValue.isEmpty()) {
            throw new IllegalStateException();
        }
        return optionalEntityAttributeValue.get();
    }


     public EntityAttributeValue findByEntityType(String entityType) {
        Optional<EntityAttributeValue> optionalEntityAttributeValue = entityAttributeValueRepository.findByEntityType(entityType);
        if (optionalEntityAttributeValue.isEmpty()) {
            throw new IllegalArgumentException();
        }
        EntityAttributeValue entityAttributeValue = optionalEntityAttributeValue.get();
        return entityAttributeValue;
    }
}
