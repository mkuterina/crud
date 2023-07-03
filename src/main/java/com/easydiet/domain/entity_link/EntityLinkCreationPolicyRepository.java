package com.easydiet.domain.entity_link;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntityLinkCreationPolicyRepository {

    private final EntityLinkRuleRepository entityLinkRuleRepository;
    public EntityLinkCreationPolicy findByLinkType(EntityLinkType entityLinkType) {
        List<EntityLinkRule> rules = entityLinkRuleRepository.findAllByLinkType(entityLinkType);
        return new EntityLinkCreationPolicy(rules);
    }
}
