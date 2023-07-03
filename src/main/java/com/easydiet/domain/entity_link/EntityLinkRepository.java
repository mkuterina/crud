package com.easydiet.domain.entity_link;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntityLinkRepository extends JpaRepository<EntityLink, String> {

    List<EntityLink> findAllByTypeAndOriginIdAndOriginTypeAndDestinationIdAndDestinationType(
            EntityLinkType type,
            String originId,
            EntityType originType,
            String destinationId,
            EntityType destinationType
    );
}
