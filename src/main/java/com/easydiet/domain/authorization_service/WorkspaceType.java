package com.easydiet.domain.authorization_service;

import com.easydiet.domain.EntityStatus;
import com.easydiet.domain.EntityStatusConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "workspace_type")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WorkspaceType {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "delete_date")
    private LocalDateTime deleteDate;

    @Column(name = "group_status")
    @Convert(converter = EntityStatusConverter.class)
    private EntityStatus status;

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || (other.getClass() != this.getClass())) {
            return false;
        }
        WorkspaceType otherType = (WorkspaceType) other;
        return this.name.equals(otherType.name);
    }
}
