package com.easydiet.domain.directory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DirectoryRepository  extends JpaRepository<Directory, String> {
    @Query(value = "select d from Directory d where directoryId = :directoryId and (status = 'ENABLED' or status = 'ENABLED_BY_ID')")
    Optional<Directory> findByIdentifier(String directoryId);

    @Query(value = "select d from Directory d where status = 'ENABLED' or status = 'ENABLED_IN_LIST'")
    List<Directory> listAll();

    @Query(value = "select d from Directory d where directoryName =:directoryName and d.status != 'DISABLED'")
    Optional<Directory> findByName(String name);

}
