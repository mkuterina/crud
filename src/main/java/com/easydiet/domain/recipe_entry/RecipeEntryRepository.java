package com.easydiet.domain.recipe_entry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RecipeEntryRepository extends JpaRepository<RecipeEntry, String> {
 @Query(value = "select r from RecipeEntry r where id = :id and (status = 'ENABLED' or status = 'ENABLED_BY_ID')")
 Optional<RecipeEntry> findByIdentifier(String id);

 @Query(value = "select r from RecipeEntry r where status = 'ENABLED' or status = 'ENABLED_IN_LIST'")
 List<RecipeEntry> listAll();

 @Query(value = "select r from RecipeEntry r where directory_id = :directory_id and (status = 'ENABLED' or status = 'ENABLED_IN_LIST')")
 Collection<RecipeEntry> listAllByDirectoryId(@Param("directory_id") String directoryId);

}