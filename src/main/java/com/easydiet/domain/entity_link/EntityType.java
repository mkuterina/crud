package com.easydiet.domain.entity_link;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "entity_type")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EntityType {
    @Id
    private String code;
    private String description;
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @Column(name = "delete_date")
    private LocalDateTime deleteDate;
    private String status;
}
