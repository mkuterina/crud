package com.easydiet.service.entity_attribute;

import com.easydiet.domain.entity_attribute.EntityTypeAttribute;
import com.easydiet.domain.entity_attribute.EntityTypeAttributeName;
import com.easydiet.domain.entity_attribute.EntityTypeAttributeRepository;
import com.easydiet.domain.entity_link.EntityType;
import com.easydiet.domain.entity_link.EntityTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EntityTypeAttributeService {

    private final EntityTypeRepository entityTypeRepository;
    private final EntityTypeAttributeRepository entityTypeAttributeRepository;

    public Optional<EntityTypeAttribute> findByEntityTypeAndName(String entityType, String name) {

        Optional<EntityType> optionalEntityType = entityTypeRepository.findByCode(entityType);
        if (optionalEntityType.isEmpty()) {
            throw new IllegalStateException();
        }
        EntityType et = optionalEntityType.get();

        EntityTypeAttributeName an = EntityTypeAttributeName.create(name);

        return entityTypeAttributeRepository.findByEntityTypeAndName(et, an);
    }
}
