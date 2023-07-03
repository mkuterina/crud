package com.easydiet.domain.entity_link;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntityTypeRepository extends JpaRepository<EntityType, String> {
    Optional<EntityType> findByCode(String code);
}
