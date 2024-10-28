package com.easydiet.service.entity_attribute;

import com.easydiet.domain.OperationForbiddenException;
import com.easydiet.domain.authorization_service.AuthorizationService;
import com.easydiet.domain.authorization_service.Role;
import com.easydiet.domain.entity_attribute.*;
import com.easydiet.domain.entity_link.EntityType;
import com.easydiet.domain.entity_link.EntityTypeRepository;
import com.easydiet.service.ingredient_entry.IngredientEntryNotFoundException;
import lombok.NonNull;
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
    private final AuthorizationService authorizationService;

    @NonNull
    public EntityAttributeValue create(String entityTypeCode,
                                                   String entityId,
                                                   EntityTypeAttributeName attributeName,
                                                   String value, String userId)
            throws OperationForbiddenException {
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
        String workspaceId = optionalEntityTypeAttribute.get().getWorkspaceId();

        List<Role> roles = authorizationService.getRoles(userId, workspaceId);
        if (!roles.contains(Role.ADMINISTRATOR) && !roles.contains(Role.OWNER)) {
            throw new OperationForbiddenException("Пользовтель с таким уровнем доступа не может задавать значения атрибутов.");
        }

        EntityAttributeValue entityAttributeValue = EntityAttributeValue.create(entityType, entityId, entityTypeAttribute, value, workspaceId);
        entityAttributeValueRepository.save(entityAttributeValue);

        return entityAttributeValue;
    }
    public boolean delete(String id, String userId) throws OperationForbiddenException {
        Optional<EntityAttributeValue> optionalEntityAttributeValue = entityAttributeValueRepository.findByIdentifier(id);
        if (optionalEntityAttributeValue.isEmpty()) {
            return false;
        } else {
            EntityAttributeValue entityAttributeValue = optionalEntityAttributeValue.get();
            boolean result = entityAttributeValue.delete();


            String workspaceId = optionalEntityAttributeValue.get().getWorkspaceId();

            List<Role> roles = authorizationService.getRoles(userId, workspaceId);
            if (!roles.contains(Role.ADMINISTRATOR) && !roles.contains(Role.OWNER)) {
                throw new OperationForbiddenException("Пользовтель с таким уровнем доступа не может удалить значение атрибута.");
            }

            entityAttributeValueRepository.save(entityAttributeValue);
            return result;
        }
    }

    // TODO: to finish the method

    public List<EntityAttributeValue> findByEntityIdAndEntityType(String entityId, String entityType, String userId)
    throws OperationForbiddenException {

        return entityAttributeValueRepository.findByEntityIdAndEntityType(entityId, entityType).stream()
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

/*
  public List<EntityAttributeValue> findAllByEntityId(String entityId) {

       return entityAttributeValueRepository.findAllByEntityId(entityId).stream()
               .filter(EntityAttributeValue -> !EntityAttributeValue.isDeleted())
               .toList();
    }
 */