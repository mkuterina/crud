package com.easydiet.domain.authorization_service;

import com.easydiet.domain.EntityStatus;
import com.easydiet.domain.EntityStatusConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Entity
@Table(name = "user_ws_assignment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserWorkspaceAssignment {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "workspace_id")
    private String workspaceId;

    @Column(name = "role")
    @Convert(converter = RoleConverter.class)
    private Role role;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "delete_date")
    private LocalDateTime deleteDate;

    @Column(name = "group_status")
    @Convert(converter = EntityStatusConverter.class)
    private EntityStatus status;
}
