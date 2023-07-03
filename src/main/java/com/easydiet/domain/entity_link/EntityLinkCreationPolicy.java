package com.easydiet.domain.entity_link;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class EntityLinkCreationPolicy {

    private final List<EntityLinkRule> rules;
    public boolean allowsCreationLink(EntityType originType, EntityType destinationType) {
        for (EntityLinkRule rule : rules) {
            if (rule.allows(originType, destinationType)) {
                return true;
            }
        }
        return false;
    }
}
