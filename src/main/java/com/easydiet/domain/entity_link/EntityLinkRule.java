package com.easydiet.domain.entity_link;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "entity_link_rule")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EntityLinkRule {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "link_type", referencedColumnName = "code")
    private EntityLinkType linkType;

    @ManyToOne
    @JoinColumn(name = "allowed_origin_type", referencedColumnName = "code")
    private EntityType allowedOriginType;

    @ManyToOne
    @JoinColumn(name = "allowed_destination_type", referencedColumnName = "code")
    private EntityType allowedDestinationType;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "delete_date")
    private LocalDateTime deleteDate;
    private String status;

    public boolean allows(EntityLinkType linkType, EntityType originType, EntityType destinationType) {
        return (this.linkType.equals(linkType) && allowedOriginType.equals(originType) && allowedDestinationType.equals(destinationType));
    }

    public boolean isDeleted() {
        return deleteDate != null;
    }
}
