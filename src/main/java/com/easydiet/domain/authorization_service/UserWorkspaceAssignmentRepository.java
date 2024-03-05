package com.easydiet.domain.authorization_service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserWorkspaceAssignmentRepository extends JpaRepository<UserWorkspaceAssignment, String> {

    @Query(value = "select u from UserWorkspaceAssignment u where id = :id and (status = 'ENABLED' or status = 'ENABLED_BY_ID')")
    Optional<UserWorkspaceAssignment> findById(@Param("id") String id);

    @Query(value = "select u from UserWorkspaceAssignment u where userId = :userId and status != 'DISABLED'")
    Optional<UserWorkspaceAssignment> findByUserId(@Param("userId") String userId);

    @Query(value = "select u from UserWorkspaceAssignment u where workspaceId = :userId and status != 'DISABLED'")
    Optional<UserWorkspaceAssignment> findByWorkspaceId(@Param("workspaceId") String workspaceId);

    @Query(value = "select u from UserWorkspaceAssignment u where role = :role and status != 'DISABLED'")
    Optional<UserWorkspaceAssignment> findByRole(@Param("role") String role);

    @Query(value = "select u from UserWorkspaceAssignment u where userId = :userId and workspaceId = :workspaceId and status != 'DISABLED'")
    List<UserWorkspaceAssignment> findAllByUserIdAndDestinationIdAndWorkspaceId(UserWorkspaceAssignment userId, UserWorkspaceAssignment workspaceId);

}
