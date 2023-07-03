package com.easydiet.domain.entity_link;

import com.easydiet.domain.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "entity_link_type")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EntityLinkType {
    @Id
    private String code;
    private String description;
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @Column(name = "delete_date")
    private LocalDateTime deleteDate;
    private String status;

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || (other.getClass() != this.getClass())) {
            return false;
        }
        EntityLinkType otherType = (EntityLinkType) other;
        return this.code.equals(otherType.code);
    }
}
