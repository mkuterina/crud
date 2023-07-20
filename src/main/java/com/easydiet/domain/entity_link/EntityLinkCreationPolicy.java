package com.easydiet.domain.entity_link;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class EntityLinkCreationPolicy {

    private final List<EntityLinkRule> rules;
    public boolean allowsCreationLink(EntityLinkType linkType, EntityType originType, EntityType destinationType) {
        for (EntityLinkRule rule : rules) {
            if (rule.allows(linkType, originType, destinationType)) {
                return true;
            }
        }
        return false;
    }
}
