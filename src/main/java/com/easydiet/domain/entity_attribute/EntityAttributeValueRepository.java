package com.easydiet.domain.entity_attribute;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EntityAttributeValueRepository extends JpaRepository<EntityAttributeValue, String> {

      @Query("SELECT e FROM EntityAttributeValue e WHERE e.status != 'DISABLED'")
      List<EntityAttributeValue> listAll();

      @Query(value = "select e from EntityAttributeValue e where id = :id and (status = 'ENABLED' or status = 'ENABLED_BY_ID')")
      Optional<EntityAttributeValue> findByIdentifier(@Param("id") String id);


      @Query(value = "select e from EntityAttributeValue e where entityType = :entityType and (status = 'ENABLED' or status = 'ENABLED_IN_LIST')")
      Optional<EntityAttributeValue> findByEntityType(@Param("entityType") String entityType);

      @Query(value = "select e from EntityAttributeValue e where entityId = :entityId and (status = 'ENABLED' or status = 'ENABLED_IN_LIST')")
      List<EntityAttributeValue> findAllByEntityId(@Param("entityId") String entityId);
}
