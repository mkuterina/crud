package com.easydiet.domain.entity_link;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EntityLinkRepository extends JpaRepository<EntityLink, String> {

    List<EntityLink> findAllByTypeAndOriginIdAndOriginTypeAndDestinationIdAndDestinationType(
            EntityLinkType type,
            String originId,
            EntityType originType,
            String destinationId,
            EntityType destinationType
    );

    @Query(value = "select e from EntityLink e where id = :id and (status = 'ENABLED' or status = 'ENABLED_BY_ID')")
    Optional<EntityLink> findByIdentifier(String id);

    @Query(value = "select e from EntityLink e where status != 'DISABLED'")
    List<EntityLink> listAll();

    @Query("SELECT e FROM EntityLink e WHERE e.status != 'DISABLED'")
    List<EntityLink> findAll();

    @Query(value = "select e from EntityLink e where originType = :originType and destinationType = :destinationType and destinationId = :destinationId and status != 'DISABLED'")
    List<EntityLink> findAllByOriginTypeAndDestinationIdAndDestinationType(EntityType originType, EntityType destinationType, String destinationId);




}
