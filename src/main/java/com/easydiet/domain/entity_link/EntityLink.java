package com.easydiet.domain.entity_link;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "entity_link")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EntityLink {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "code")
    private EntityLinkType type;

    @Column(name = "origin_id")
    private String originId;

    @ManyToOne
    @JoinColumn(name = "origin_type", referencedColumnName = "code")
    private EntityType originType;

    @Column(name = "destination_id")
    private String destinationId;

    @ManyToOne
    @JoinColumn(name = "destination_type", referencedColumnName = "code")
    private EntityType destinationType;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "delete_date")
    private LocalDateTime deleteDate;
    private String status;

    public static EntityLink create(
            EntityLinkType entityLinkType,
            String originId,
            EntityType originType,
            String destinationId,
            EntityType destinationType
    ) {
        String id = UUID.randomUUID().toString();
        LocalDateTime createDate = LocalDateTime.now();

        return new EntityLink(
                id,
                entityLinkType,
                originId,
                originType,
                destinationId,
                destinationType,
                createDate,
                null,
                "ENABLED"
        );
    }
}
