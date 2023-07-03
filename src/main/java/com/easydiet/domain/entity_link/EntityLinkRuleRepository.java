package com.easydiet.domain.entity_link;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntityLinkRuleRepository extends JpaRepository<EntityLinkRule, String> {
    List<EntityLinkRule> findAllByLinkType(EntityLinkType entityLinkType);
}
