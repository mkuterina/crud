package com.easydiet.service.entity_link;

import com.easydiet.domain.entity_link.EntityLinkRule;
import com.easydiet.domain.entity_link.EntityLinkRuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EntityLinkRuleService {
    private final EntityLinkRuleRepository entityLinkRuleRepository;

    public void list() {
        List<EntityLinkRule> entityLinkRules = entityLinkRuleRepository.findAll();

    }
}
