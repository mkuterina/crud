package com.easydiet.domain.entity_attribute;

import com.easydiet.domain.entity_link.EntityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface EntityTypeAttributeRepository extends JpaRepository<EntityTypeAttribute, String> {

      @Query("SELECT e FROM EntityTypeAttribute e WHERE e.status != 'DISABLED'")
      List<EntityTypeAttribute> findAll();

      @Query(value = "select e from EntityTypeAttribute e where id = :id and (status = 'ENABLED' or status = 'ENABLED_BY_ID')")
      Optional<EntityTypeAttribute> findById(@Param("id") String id);


      @Query(value = "select e from EntityTypeAttribute e where entityType = :entityType and (status = 'ENABLED' or status = 'ENABLED_IN_LIST')")
      Optional<EntityTypeAttribute> findByEntityType (@Param("entityType") String entityType);

      @Query(value = "select e from EntityTypeAttribute e where name = :name and (status = 'ENABLED' or status = 'ENABLED_IN_LIST')")
      Optional<EntityTypeAttributeName> findByName (@Param("name") String name);

      @Query(value = "select e from EntityTypeAttribute e where name = :name and entityType = :entityType and status != 'DISABLED'")
      List<EntityTypeAttribute> findByEntityTypeAndName( EntityTypeAttribute entityType, EntityTypeAttributeName name);

      @Query(value = "select e from EntityTypeAttribute e where name = :name and entityType = :entityType and status != 'DISABLED'")
      Optional<EntityTypeAttribute> findByNameAndEntityType(EntityTypeAttributeName attributeName, EntityType entityType);
}
