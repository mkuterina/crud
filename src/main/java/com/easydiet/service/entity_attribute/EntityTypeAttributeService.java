package com.easydiet.service.entity_attribute;

import com.easydiet.domain.entity_attribute.EntityTypeAttribute;
import com.easydiet.domain.entity_attribute.EntityTypeAttributeName;
import com.easydiet.domain.entity_attribute.EntityTypeAttributeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EntityTypeAttributeService {

    private final EntityTypeAttributeRepository entityTypeAttributeRepository;

    public List<EntityTypeAttribute> findByEntityTypeAndName(String entityType, String name) {

        Optional<EntityTypeAttribute> optionalEntityType = entityTypeAttributeRepository.findByEntityType(entityType);
        if (optionalEntityType.isEmpty()) {
            throw new IllegalStateException();
        }
        EntityTypeAttribute et = optionalEntityType.get();

        Optional<EntityTypeAttributeName> optionalName = entityTypeAttributeRepository.findByName(name);
        if (optionalName.isEmpty()) {
            throw new IllegalStateException();
        }
        EntityTypeAttributeName an = optionalName.get();

        return entityTypeAttributeRepository.findByEntityTypeAndName(et, an)
                .stream().filter(EntityTypeAttribute -> !EntityTypeAttribute.isDeleted()).toList();
    }
}
