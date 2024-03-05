package com.easydiet.domain.authorization_service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT u FROM User u WHERE u.status != 'DISABLED'")
    List<User> findAll();

    @Query(value = "select u from User u where id = :id and (status = 'ENABLED' or status = 'ENABLED_BY_ID')")
    Optional<User> findById(@Param("id") String id);

    @Query(value = "select u from User u where fullName = :fullName and status != 'DISABLED'")
    Optional<User> findByFullName(@Param("fullName") String fullName);

    @Query(value = "select u from User u where email = :email and status != 'DISABLED'")
    Optional<User> findByEmail(@Param("email") String email);

    @Query(value = "select u from User u where login = :login and status != 'DISABLED'")
    Optional<User> findByLogin(@Param("login") String login);

    @Query(value = "select u from User u where status = 'ENABLED' or status = 'ENABLED_IN_LIST'")
    List<User> listAll();

    @Query(value = "select u from User u where fullName = :fillName and (status = 'ENABLED' or status = 'ENABLED_IN_LIST')")
    Collection<User> listAllByFullName(@Param("fullName") String fullName);
}

