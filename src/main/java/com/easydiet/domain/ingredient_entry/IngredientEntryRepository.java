package com.easydiet.domain.ingredient_entry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IngredientEntryRepository extends JpaRepository<IngredientEntry, String> {
    @Query(value = "select i from IngredientEntry i where id = :id and (status = 'ENABLED' or status = 'ENABLED_BY_ID')")
    Optional<IngredientEntry> findByIdentifier(String id);

    @Query(value = "select i from IngredientEntry i where status = 'ENABLED' or status = 'ENABLED_IN_LIST'")
    List<IngredientEntry> listAll();

    @Query(value = "select i from IngredientEntry i where directoryId = :directoryId and (status = 'ENABLED' or status = 'ENABLED_IN_LIST')")
    Collection<IngredientEntry> listAllByDirectoryId(@Param("directoryId") String directoryId);
}


