package com.easydiet.domain.recipe_line;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RecipeLineRepository extends JpaRepository<RecipeLine, String> {
    @Query(value = "select r from RecipeLine r where id = :id and (status = 'ENABLED' or status = 'ENABLED_BY_ID')")
    Optional<RecipeLine> findByIdentifier(@Param("id") String id);

    @Query(value = "select r from RecipeLine r where status = 'ENABLED' or status = 'ENABLED_IN_LIST'")
    List<RecipeLine> listAll();

    @Query(value = "select r from RecipeLine r where recipe_entry_id = :recipe_entry_id and (status = 'ENABLED' or status = 'ENABLED_IN_LIST')")
    Collection<RecipeLine> listAllByRecipeEntryId(@Param("recipe_entry_id") String recipeEntryId);


}
