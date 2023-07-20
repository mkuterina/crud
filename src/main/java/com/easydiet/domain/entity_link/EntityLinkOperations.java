package com.easydiet.domain.entity_link;

import com.easydiet.domain.OperationForbiddenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntityLinkOperations {

    public EntityLink createEntityLink(
            EntityLinkType entityLinkType,
            String originId,
            EntityType originType,
            String destinationId,
            EntityType destinationType,
            EntityLinkCreationPolicy policy,
            List<EntityLink> alreadyDefinedLinks
    ) throws
            OperationForbiddenException
    {
        if (!policy.allowsCreationLink(entityLinkType, originType, destinationType)) {
            throw new OperationForbiddenException("Запрещено создавать связи с такими типами origin и destination");
        }

        if (!alreadyDefinedLinks.isEmpty()) {
            return alreadyDefinedLinks.get(0);
        }

        return EntityLink.create(
                entityLinkType,
                originId,
                originType,
                destinationId,
                destinationType
        );
    }
}
