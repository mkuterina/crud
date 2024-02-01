package com.easydiet.domain.workspace;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkspaceTypeRepository extends JpaRepository<WorkspaceType, String> {


    @Query(value = "select w from WorkspaceType w where id = :id and (status = 'ENABLED' or status = 'ENABLED_BY_ID')")
    Optional<WorkspaceType> findByIdentifier(String id);
    Optional<WorkspaceType> findByName(String name);
}
