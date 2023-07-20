package com.easydiet.domain.entity_link;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntityLinkRuleRepository extends JpaRepository<EntityLinkRule, String> {

    @Query("SELECT r FROM EntityLinkRule r WHERE r.status != 'DISABLED'")
    List<EntityLinkRule> findAll();

    List<EntityLinkRule> findAllByLinkType(EntityLinkType entityLinkType);
}
