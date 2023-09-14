package com.easydiet.domain.entity_attribute;

import com.easydiet.domain.entity_link.EntityType;
import org.jetbrains.annotations.NotNull;
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


      @Query(value = "select e from EntityTypeAttribute e where entityType.code = :entityTypeCode and (status = 'ENABLED' or status = 'ENABLED_IN_LIST')")
      Optional<EntityTypeAttribute> findByEntityType (@Param("entityTypeCode") String entityTypeCode);

      @Query(value = "select e from EntityTypeAttribute e where name = :name and (status = 'ENABLED' or status = 'ENABLED_IN_LIST')")
      Optional<EntityTypeAttributeName> findByName (@Param("name") String name);

      @Query(value = "select e from EntityTypeAttribute e where name = :name and entityType = :entityType and status != 'DISABLED'")
      Optional<EntityTypeAttribute> findByEntityTypeAndName( EntityType entityType, EntityTypeAttributeName name);

      @Query(value = "select e from EntityTypeAttribute e where name = :name and entityType = :entityType and status != 'DISABLED'")
      Optional<EntityTypeAttribute> findByNameAndEntityType(@Param("name") EntityTypeAttributeName attributeName, EntityType entityType);
}
