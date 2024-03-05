package com.easydiet.domain.authorization_service;

import com.easydiet.domain.EntityStatus;
import com.easydiet.domain.EntityStatusConverter;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Entity
@Table(name = "workspace")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Workspace {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "workspace_name")
    private String name;

    @Column(name = "workspace_type")
    private String type;

    @Column(name = "workspace_description")
    private String description;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "delete_date")
    private LocalDateTime deleteDate;

    @Column(name = "group_status")
    @Convert(converter = EntityStatusConverter.class)
    private EntityStatus status;
}
