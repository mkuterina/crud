package com.easydiet.domain.group_entry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface GroupEntryRepository extends JpaRepository<GroupEntry, String> {
    @Query("SELECT g FROM GroupEntry g WHERE g.status != 'DISABLED'")
    List<GroupEntry> findAll();

    @Query(value = "select g from GroupEntry g where id = :id and (status = 'ENABLED' or status = 'ENABLED_BY_ID')")
    Optional<GroupEntry> findByIdentifier(String id);

    @Query(value = "select g from GroupEntry g where status = 'ENABLED' or status = 'ENABLED_IN_LIST'")
    List<GroupEntry> listAll();

    @Query(value = "select g from GroupEntry g where directoryId = :directoryId and (status = 'ENABLED' or status = 'ENABLED_IN_LIST')")
    Collection<GroupEntry> listAllByDirectoryId(
            @Param("directoryId") String directoryId);

}
