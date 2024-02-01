package com.easydiet.domain.workspace;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, String> {
    @Query(value = "select w from Workspace e where id = :id and (status = 'ENABLED' or status = 'ENABLED_BY_ID')")
    Optional<Workspace> findByIdentifier(String id);

}
